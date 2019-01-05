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
 * Servlet implementation class MyinfoupdateServlet
 */
public class MyinfoupdateServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//将表单提交的数据封装到javabean中的实体类中
		User user=RequestBeanUtils.requestToSimpleBean(request, User.class);
		//调用业务逻辑层的更新的方法
		UserService service=new UserService();
		String gemail=request.getParameter("email");
		String gusername=request.getParameter("username");
		System.out.println(gemail);
		String regex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
		String regExp = "[a-zA-Z]{2,}";
		if(gusername.equals("") || gusername==null) {
			request.setAttribute("info", "昵称不能为空，请重新修改");
			request.getRequestDispatcher("/client/my_info.jsp").forward(request, response);
		}
		if(!gusername.matches(regExp)) {
			request.setAttribute("info", "昵称至少为2个字母(只能字母)，请重新修改");
			request.getRequestDispatcher("/client/my_info.jsp").forward(request, response);
		}
		else if(gemail.equals("")) {
			request.setAttribute("info", "邮箱不能为空，请重新修改");
			request.getRequestDispatcher("/client/my_info.jsp").forward(request, response);
		}
		else if(gemail.matches(regex)) {
			System.out.println("合法");
			boolean mark=service.updateUser(user);
			if(mark){
				request.setAttribute("info", "用户信息修改成功");
			}else{
				request.setAttribute("info", "用户信息修改失败");
			}
			request.getRequestDispatcher("/admin/my_info.jsp").forward(request, response);
		}
		else {
			System.out.println("不合法");
			request.setAttribute("info", "邮箱不合法，请重新修改");
			request.getRequestDispatcher("/admin/my_info.jsp").forward(request, response);
		}
	}
	
	
}
