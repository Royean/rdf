package clientServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet 
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		//获取用户信息 
        String curuserid = String.valueOf(request.getSession().getAttribute("curuserid"));  
        @SuppressWarnings("unchecked")
		ArrayList<String> alluserid = (ArrayList<String>)this.getServletContext().getAttribute("alluserid"); 
        for(String u:alluserid){
            //将这个用户从ServletContext对象中移除   
            if(curuserid.equals(u)){
                 alluserid.remove(u);    
                 break;   
            }
        }
		// 获取session对象.
		HttpSession session = request.getSession();
		// 销毁session
		session.invalidate();
		// flag标识
		String flag = request.getParameter("flag");
		if (flag == null || flag.trim().isEmpty()) 
		{
			// 重定向到首页
			response.sendRedirect(request.getContextPath() + "/search.jsp");
		}
		//response.sendRedirect(request.getContextPath() + "/client/search.jsp");
		System.out.println("销毁session");
	}

}
