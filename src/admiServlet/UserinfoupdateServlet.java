package admiServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;

import beanEntity.User;
import service.UserService;

/**
 * Servlet implementation class UserinfoupdateServlet
 */
public class UserinfoupdateServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//将从表单中获取的数据封装到javabean的用户信息实体类中
		User user=RequestBeanUtils.requestToSimpleBean(request, User.class);
		UserService service=new UserService();
		//调用service业务逻辑层的getUser方法
		User userInfo;
		try {
			userInfo = service.getUser(user);
			//将返回的用户信息设置域中
			request.setAttribute("user", userInfo);
			//转发到更新用户信息的页面
			request.getRequestDispatcher("/admin/user_update.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//将表单提交的数据封装到javabean中的实体类中
		User user=RequestBeanUtils.requestToSimpleBean(request, User.class);
		//调用业务逻辑层的更新的方法
		UserService service=new UserService();
		String gemail=request.getParameter("email");
		String gusername=request.getParameter("username");
		String role=request.getParameter("role");
		String pwd=request.getParameter("password");
		System.out.println(gemail);
		String regex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
		String regExp = "[a-zA-Z]{2,}";
		String regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9_!@#$%^&*()]{8,16}$";
		if(gusername.equals("") || gusername==null) {
			request.setAttribute("info", "昵称不能为空，请重新修改");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		if(!gusername.matches(regExp)) {
			request.setAttribute("info", "昵称至少为2个字母(只能字母)，请重新修改");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else if(pwd.equals("")) {
			request.setAttribute("info", "密码不能为空，请重新修改");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else if(!pwd.matches(regexp)) {
			request.setAttribute("info", "至少一个小写字母，一个大写字母和一个数字，8-16位,特殊符号只允许_!@#$%^&*()");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else if(role.equals("")) {
			request.setAttribute("info", "角色不能为空，请重新修改");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else if(!role.equals("1") && !role.equals("2")) {
			request.setAttribute("info", "角色只能为1、2，请重新修改");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else if(gemail.equals("")) {
			request.setAttribute("info", "邮箱不能为空，请重新修改");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else if(gemail.matches(regex)) {
			System.out.println("合法");
			boolean mark=service.updateUser(user);
			if(mark){
				request.setAttribute("info", "用户信息修改成功");
			}else{
				request.setAttribute("info", "用户信息修改失败");
			}
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else {
			System.out.println("不合法");
			request.setAttribute("info", "邮箱不合法，请重新修改");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
	
	
}
}
