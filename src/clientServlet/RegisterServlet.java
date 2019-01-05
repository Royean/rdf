 package clientServlet;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import service.UserService;

import dao.UserDao;
import beanEntity.User;
import exception.RegisterException;
import utils.ActiveCodeUtils;

public class RegisterServlet extends HttpServlet {
/**
* Destruction of the servlet. 

*/
public void destroy() 
{
   super.destroy(); // Just puts "destroy" string in log
   // Put your code here
}

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
   response.setContentType("text/html");
   doPost(request,response);
}

public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
{
   System.out.println("!!!!!!!!!!!!!!");
   response.setContentType("text/html");
   response.setCharacterEncoding("utf-8");
   String email1 = request.getParameter("email");
   String name = new String(request.getParameter("username").getBytes("ISO8859_1"),"utf-8");
   String pwd = request.getParameter("password");
   String tel = request.getParameter("telephone");
   
   Date current_date = new Date(); 
   SimpleDateFormat  SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   String date = SimpleDateFormat.format(current_date.getTime());
   String code = ActiveCodeUtils.createActiveCode();
   User user = new User();
   user.setEmail(email1);
   user.setUsername(name);
   user.setPassword(pwd);
   user.setTelephone(tel);
   user.setRegistTime(date);
   user.setActiveCode(code);
   
   UserService service = new UserService();
   try 
   {
		service.activate(user);
		request.getSession().setAttribute("rid", user.getId());
   }
   catch (AddressException e) 
   {
		// TODO Auto-generated catch block
		e.printStackTrace();
   } 
   catch (SQLException e) 
   {
		// TODO Auto-generated catch block
		e.printStackTrace();
   } 
   catch (MessagingException e) 
   {
		// TODO Auto-generated catch block
		e.printStackTrace();
   }
   catch (Exception e) 
   {
			// TODO Auto-generated catch block
			e.printStackTrace();
   }
   //request.getSession().setAttribute("rid", user.getId());
   request.getSession().setAttribute("ruser", user);
   response.sendRedirect(request.getContextPath()+"/client/registerSuccess.jsp");
}

public void init() throws ServletException 
{
   // Put your code here
}

}