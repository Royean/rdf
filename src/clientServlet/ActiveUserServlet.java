package clientServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import exception.ActiveUserException;

import service.UserService;

public class ActiveUserServlet extends HttpServlet 
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		// 1.获取激活码
		String code = request.getParameter("code");

		// 2.调用激活用户操作
		UserService service = new UserService();
		try 
		{
			service.activeUser(code);
			response.sendRedirect(request.getContextPath()+"/client/activesuccess.jsp");
		} 
		catch (ActiveUserException e) 
		{
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (java.text.ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
