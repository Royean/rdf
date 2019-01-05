package clientServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import service.UserService;

/**
 * Servlet implementation class ResetsuccessServlet
 */
public class ResetsuccessServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String remail = request.getParameter("forget-email");
		int idd = Integer.parseInt(request.getParameter("idd"));
		String rpwd = request.getParameter("rpwd");
		UserDao dao = new UserDao();
		try {
			dao.changePassword(idd,rpwd);
			request.setAttribute("info", "重设密码成功");
			//response.sendRedirect(request.getContextPath() + "/client/RegisterLogin.jsp");
			request.getRequestDispatcher("/client/reset_info.jsp").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("info", "重设密码失败");
			request.getRequestDispatcher("/client/reset_info.jsp").forward(request, response);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
