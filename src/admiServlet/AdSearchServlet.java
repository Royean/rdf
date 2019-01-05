package admiServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beanEntity.Key;
import dao.KeyDao;

/**
 * Servlet implementation class AdSearchServlet
 */
public class AdSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String keyword=new String(request.getParameter("keyword").getBytes("ISO8859_1"),"utf-8");
		Date current_date = new Date(); 
		SimpleDateFormat  SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = SimpleDateFormat.format(current_date.getTime());
		HttpSession session = request.getSession();
		//从session中获取对象
		System.out.println(session.getAttribute("userid"));
		System.out.println("!!!!");
		//如果是匿名输入uid默认为10001;s
		int uid = 10001;
		if(session.getAttribute("userid")!=null)
		{
			uid=(int)session.getAttribute("userid");
			System.out.println("!!!!");
		}  
		Key key = new Key();
	    key.setUserId(uid);
		key.setKeyWord(keyword);
		key.setInputTime(date);
		KeyDao dao = new KeyDao();
		try 
		{
			   int key_id=(dao.addKey(key)).intValue();
			   System.out.println(key_id);
			   response.sendRedirect(request.getContextPath()+"/admin/adminResult.jsp?keyword="+keyword+"&key_id="+key_id);
		} 
		catch (SQLException e) 
		{
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		}
		System.out.println("测试成功！！");
	}

}
