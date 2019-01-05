package admiServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beanEntity.User;
import dao.UserDao;

import javax.servlet.ServletRequest;
/**
 * Servlet implementation class FindmyinfoServlet
 */
public class FindmyinfoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int myid=(Integer)session.getAttribute("userid");
        User user = new User();
        user.setId(myid);
        UserDao dao = new UserDao();
        try {
			user = dao.findUserByid(myid);
			request.setAttribute("myuser",user);
			request.getRequestDispatcher("admin/myInfo.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
