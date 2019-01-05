package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.sun.mail.util.MailSSLSocketFactory;
/**
 * 发送邮件的工具类
 */
public class MailUtils 
{
	//private static final Logger logger = LogManager.getLogger(MailUtils.class);
    private final static String smtpHost = "SMTP.qq.com";//配置Email session对象
    private final static String messageType = "text/html;charset=UTF-8";//相应内容类型，编码类型
    private final static String fromEmail = "2627656663@qq.com";//发送邮件的邮箱
   // private final static String password = "btjknwltmtmkdjai";//密码

	
	
	/**
     * 
     * @param to:代表给谁发邮件
     * @param code：代表激活码是什么
     * @throws MessagingException 
     * @throws AddressException 
     */
    
		public static void sendMail(String to,String code) throws Exception
		{
	        //1.创建连接对象，连接到邮箱服务器
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";//SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            //String messageText = appendString(id, username);
            //第一步：配置javax.mail.Session对象
            Properties props = new Properties();   // 创建Properties 类用于记录邮箱的一些属性

            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);

            props.put("mail.smtp.host", smtpHost);  //此处填写SMTP服务器
            props.put("mail.smtp.starttls.enable", "true");//使用 STARTTLS安全连接
            props.put("mail.smtp.port", "465");             //google使用465或587端口
            props.put("mail.smtp.auth", "true");       // 表示SMTP发送邮件，必须进行身份验证
            props.put("mail.debug", "true");      //开启调试模式
            props.put("mail.transport.protocol", "SMTP");     // 发送邮件协议名称
            props.setProperty("mail.smtp.socketFactory.port", "465");
         // 创建验证器
         			Authenticator auth = new Authenticator() 
         			{
         				public PasswordAuthentication getPasswordAuthentication() 
         				{
         					return new PasswordAuthentication("2627656663", "btjknwltmtmkdjai");
         				}
         			};
         	        
         			Session session = Session.getInstance(props, auth);
            //Session mailSession = Session.getInstance(props, new MyAuthenticator(fromEmail, password));//此处填写你的账号和口令(16位口令)
            props.put("mail.smtp.ssl.socketFactory", sf);					
	        //2.创建邮件对象
	        MimeMessage message = new MimeMessage(session);
	          //2.1设置发件人
	            /**
	             * service@mac.com是一个字符串，
	             * 将字符串转换成地址：new InternetAddress("service@mac.com")
	             */
	           message.setFrom(new InternetAddress("2627656663@qq.com"));
	        
	         //2.2设置收件人
	         message.setRecipient(RecipientType.TO, new InternetAddress(to));
	        
	         //2.3设置邮件主题
	         message.setSubject("这是一封激活邮件！");
	        
	         //2.4设置邮件正文
	         /**
	          *   ?code="+code+"  :表示从外面传入一个激活码
	          */
	         message.setContent("<h1>来自RDF搜索引擎的激活邮件，激活请点击以下链接：</h1> <h3>  <a href='http://47.92.162.51:8080/rdf2/activeUser?code="+code+"'> http://47.92.162.51:8080/rdf2/activeUser?code="+code+" </a></h3>", "text/html;charset=UTF-8");
	        
	         //3.发送一封激活邮件
	         Transport.send(message);
	    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		public static void sendMailreset(String to,int id) throws Exception
		{
			//1.创建连接对象，连接到邮箱服务器
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";//SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            //String messageText = appendString(id, username);
            //第一步：配置javax.mail.Session对象
            Properties props = new Properties();   // 创建Properties 类用于记录邮箱的一些属性

            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);

            props.put("mail.smtp.host", smtpHost);  //此处填写SMTP服务器
            props.put("mail.smtp.starttls.enable", "true");//使用 STARTTLS安全连接
            props.put("mail.smtp.port", "465");             //google使用465或587端口
            props.put("mail.smtp.auth", "true");       // 表示SMTP发送邮件，必须进行身份验证
            props.put("mail.debug", "true");      //开启调试模式
            props.put("mail.transport.protocol", "SMTP");     // 发送邮件协议名称
            props.setProperty("mail.smtp.socketFactory.port", "465");
         // 创建验证器
         			Authenticator auth = new Authenticator() 
         			{
         				public PasswordAuthentication getPasswordAuthentication() 
         				{
         					return new PasswordAuthentication("2627656663", "btjknwltmtmkdjai");
         				}
         			};
         	        
         			Session session = Session.getInstance(props, auth);
            //Session mailSession = Session.getInstance(props, new MyAuthenticator(fromEmail, password));//此处填写你的账号和口令(16位口令)
            props.put("mail.smtp.ssl.socketFactory", sf);	
	        
	        //2.创建邮件对象
	        MimeMessage message = new MimeMessage(session);
	          //2.1设置发件人
	            /**
	             * service@mac.com是一个字符串，
	             * 将字符串转换成地址：new InternetAddress("service@mac.com")
	             */
	           message.setFrom(new InternetAddress("2627656663@qq.com"));
	        
	         //2.2设置收件人
	         message.setRecipient(RecipientType.TO, new InternetAddress(to));
	        
	         //2.3设置邮件主题
	         message.setSubject("这是一封验证邮件！");
	        
	         //2.4设置邮件正文
	         /**
	          *   ?code="+code+"  :表示从外面传入一个激活码
	          */
	         message.setContent("<h1>来自RDF搜索引擎的验证邮件，验证请点击以下链接：</h1> <h3>  <a href='http://47.92.162.51:8080/rdf2/client/resetPwd.jsp?ffid="+id+"'> http://47.92.162.51:8080/rdf2/client/resetPwd.jsp?ffid="+id+" </a></h3>", "text/html;charset=UTF-8");
	        
	         //3.发送一封激活邮件
	         Transport.send(message);
	    }
		
		
	
}
