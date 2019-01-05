package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import beanEntity.Result;
import dao.ResultDao;


/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket")
public class Websocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<Websocket> webSocketSet = new CopyOnWriteArraySet<Websocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String kw;
    private String key_id;
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        
    }

    public void read(String message) throws IOException { 	
    	Socket client = null; 
		BufferedWriter writer = null;		
		BufferedReader input = null;
		OutputStream os= null;
		String keyword=message;
		System.out.println(keyword);
		BufferedReader bufferedReader =null;
		try {
			client=new Socket("127.0.0.1", 8008);
			System.out.println("socket success");
			os = client.getOutputStream();
			os.write(keyword.getBytes());
			os.flush();
			client.shutdownOutput();
	        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        String line;
	        int count=0;
	        while (true) { 
	            line = input.readLine();
	            if(line==null) {
	            	break;	
	            }
	            count++;
	            if(count%4 == 0) {
	            	sendMessage(line);
	            	ResultDao resultdao=new ResultDao();
	                Result result=new Result();
	                result.setkey_ID(Integer.valueOf(key_id).intValue());
	                result.setresult(line);
	                try {
	        			resultdao.addResult(result);
	        		} catch (SQLException e1) {
	        			// TODO Auto-generated catch block
	        			e1.printStackTrace();
	        		}
	            	count=0;
	            }
	            
				System.out.println("搜索结果：" + line);
	    	}
//	        sendMessage(line.toString());
			
		}catch (Exception e) {
	    	e.printStackTrace();
	    }
		finally {
			if(client!=null)
	        	client.close();
			if(input!=null)
	        	input.close();
			if(os!=null){
				os.close();
			}
	    }
    }
    
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        message="ls\n"+message.replace("%20", " ");
    	System.out.println("来自客户端的消息:" + message);
    	int index = message.indexOf("&");
    	key_id = message.substring(index+1,message.length());
    	message = message.substring(0,index);
    	
        //建立一个socket 从
        try {
			read(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
    	Websocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
    	Websocket.onlineCount--;
    }
}
