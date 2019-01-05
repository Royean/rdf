package clientServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beanEntity.User;
import service.UserService;
import utils.ActiveCodeUtils;

/**
 * Servlet implementation class ResetpwdServlet
 */
public class ResetpwdServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	   response.setContentType("text/html");
	   doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
	   response.setContentType("text/html");
	   response.setCharacterEncoding("utf-8");
	   String remail = request.getParameter("forget-email");
	   int fid = Integer.parseInt(request.getParameter("fid"));
	   //request.getSession().setAttribute("ffid", fid);
	   UserService service = new UserService();
	   try {
		service.resetemail(remail,fid);
		response.sendRedirect(request.getContextPath()+"/client/emailSuccess.jsp");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		response.sendRedirect(request.getContextPath()+"/client/cannotfindUser.jsp");
		e.printStackTrace();
	}
	}

	public void init() throws ServletException 
	{
	   // Put your code here
	}

	}
