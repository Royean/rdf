package clientServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.PrintWriter;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beanEntity.User;
import dao.UserDao;
import service.UserService;

public class LoginServlet extends HttpServlet 
{

    public void destroy() 
    {
		   super.destroy(); // Just puts "destroy" string in log
		   // Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		   doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		   ServletContext context = this.getServletContext(); 
		   response.setContentType("text/html");
		   response.setCharacterEncoding("utf-8");
		   PrintWriter out = response.getWriter();
		   
		   int id1 = Integer.valueOf(request.getParameter("did"));
		   String pwd = new String(request.getParameter("dpassword").getBytes("ISO8859_1"),"utf-8");
		   
		   Date current_date = new Date(); 
		   SimpleDateFormat  SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		   String date = SimpleDateFormat.format(current_date.getTime());
		   
		   User user = new User();
		  // User user1 = null;
		   user.setId(id1);
		   user.setPassword(pwd);
		   user.setLoginTime(date);

		   String ddate=user.getLoginTime();
		   
		// 2.调用service完成登录操作。
		    UserDao dao = new UserDao();
			UserService service = new UserService();
			try 
			{
				user = service.login(id1, pwd);	
				int role = user.getRole();
				// 3.登录成功，将用户存储到session中.
				dao.updateLogintime(ddate,id1);
				//ArrayList login=new ArrayList();//实例化列表对象///////////////////////////////////////////
				//login.add(user);//把个人信息保存到列表中//////////////////////////////////////////
				//request.getSession().setAttribute("login", login);//把列表保存到session对象中，以便在别的页面中获取个人信息///////////////////////
				request.getSession().setAttribute("userid", user.getId());
				request.getSession().setAttribute("luser", user);
				
				//获取用户列表,第一次获取时候为空   
				@SuppressWarnings("unchecked")
				ArrayList<String> alluserid=(ArrayList<String>)context.getAttribute("alluserid"); 
		        //第一个用户登录时
		        if(alluserid==null){
		        	alluserid = new ArrayList<String>();     
		        	alluserid.add(String.valueOf(id1));   
		            context.setAttribute("alluserid", alluserid);   //将第一个用户的id保存到ServletContext对象中   
		        //非第一个用户登录   
		        }else{
		            for(String curuserid : alluserid){
		                //如果该用户已经登录,请求error.jsp不让其再登录
		                if((String.valueOf(id1)).equals(curuserid)){
		                	response.sendRedirect(request.getContextPath() +  "/client/cannotrelogin.jsp");
		                	return;
		                	
		                }
		            }
		            //如果该用户没登录,就将该用户的名字保存到ServletContext对象中
		            alluserid.add(String.valueOf(id1)); 
		        }
		        request.getSession().setAttribute("alluserid",alluserid);
		        
		        request.getSession().setAttribute("curuserid", id1);   //保存一下该用户信息以备后用   
				// 获取用户的角色，其中用户的角色分普通用户和超级用户两种
				// 如果是管理员，就进入到网上书城的后台管理系统；否则进入我的账户页面
				if (1==role)
				{
					response.sendRedirect(request.getContextPath() +  "/admin/admisearch.jsp");
					return;
				} 
				else 
				{
					response.sendRedirect(request.getContextPath() + "/client/search2.jsp");
					return;
				}
			} 
			catch (LoginException e) 
			{
				// 如果出现问题，将错误信息存储到request范围，并跳转回登录页面显示错误信息
				e.printStackTrace();
				request.setAttribute("register_message", e.getMessage());
				response.sendRedirect(request.getContextPath()+"/client/loginerror.jsp");
				return;
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	   
	
	public void init() throws ServletException 
	{
		   // Put your code here
	}
}

