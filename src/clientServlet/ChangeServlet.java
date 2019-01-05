package clientServlet;
import dao.UserDao;
import beanEntity.User;
import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.UserDao;
public class ChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

    public void destroy() 
    {
		   super.destroy(); // Just puts "destroy" string in log
		   // Put your code here
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		
		HttpSession session = request.getSession();
		String newpwd = request.getParameter("newpwd");
		String oldpwd = request.getParameter("oldpwd");
		String confpwd = request.getParameter("confpwd");
		String regExp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9_!@#$%^&*()]{8,16}$";
		UserDao dao  = new UserDao();
		User user = new User();
		if(oldpwd==null || oldpwd.equals(""))
		{
			request.setAttribute("info", "旧密码不能为空");
			request.getRequestDispatcher("/client/changepwd.jsp").forward(request, response);
		}
		else if(newpwd.equals("") || confpwd.equals(""))
		{
			request.setAttribute("info", "新密码不能为空");
			request.getRequestDispatcher("/client/changepwd.jsp").forward(request, response);
		}
		else if(!newpwd.matches(regExp))
		{
			request.setAttribute("info", "密码至少一个小写字母，一个大写字母和一个数字，8-16位，特殊符号只允许_!@#$%^&*(),请重新修改");
			request.getRequestDispatcher("/client/changepwd.jsp").forward(request, response);
		}
		else if(!newpwd.equals(confpwd))
		{
			request.setAttribute("info", "两次输入的密码不一致");
			request.getRequestDispatcher("/client/changepwd.jsp").forward(request, response);
		}
	
		else {
			try {
				user = dao.findUserByidAndPassword((int) session.getAttribute("userid"),oldpwd);
				if(user==null)
				{
					request.setAttribute("info", "请输入正确的密码");
					request.getRequestDispatcher("/client/changepwd.jsp").forward(request, response);
				}
				else
				{
					dao.changePassword((int) session.getAttribute("userid"),newpwd);
					request.setAttribute("info", "修改成功！");
					request.getRequestDispatcher("/client/changepwd.jsp").forward(request, response);
					return; 
				}
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
		}
		
		}
	
}
