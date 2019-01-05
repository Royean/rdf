package admiServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.my.web.servlet.RequestBeanUtils;
import beanEntity.User;
import service.UserService;

public class UserinfoinsertServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//RequestBeanUtils的使用方法，需要导入三个包。
		//commons-beanutils-1.8.3.jar  commons-logging-1.1.1.jar
		//commy-web-0.0.1.jar
		User user=RequestBeanUtils.requestToSimpleBean(request, User.class);
		System.out.println(user);//测试到这里是否出现bug
		//然后在servlet层调用service逻辑处理层
		UserService service=new UserService();
		String gemail=request.getParameter("email");
		String gusername=request.getParameter("username");
		String role=request.getParameter("role");
		String id=request.getParameter("id");	
		String pwd=request.getParameter("password");
		String regex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
		String regex1 = "[0-9][0-9][0-9][0-9][0-9]";
		String regExp = "[a-zA-Z]{2,}";
		String regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9_!@#$%^&*()]{8,16}$";
		if(gusername.equals("") || gusername==null) {
			request.setAttribute("info", "昵称不能为空，请重新添加");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		if(!gusername.matches(regExp)) {
			request.setAttribute("info", "昵称至少为2个字母(只能字母)，请重新修改");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else if(id.equals("")) {
			request.setAttribute("info", "账号不能为空，请重新添加");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else if(!id.matches(regex1)) {
			request.setAttribute("info", "账号必须为5位数，请重新添加");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else if(pwd.equals("")) {
			request.setAttribute("info", "密码不能为空，请重新添加");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else if(!pwd.matches(regexp)) {
			request.setAttribute("info","至少一个小写字母，一个大写字母和一个数字，8-16位,特殊符号只允许_!@#$%^&*()");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else if(role.equals("")) {
			request.setAttribute("info", "角色不能为空，请重新添加");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else if(!role.equals("1") && !role.equals("2")) {
			request.setAttribute("info", "角色只能为1、2，请重新添加");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else if(gemail.equals("")) {
			request.setAttribute("info", "邮箱不能为空，请重新添加");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else if(gemail.matches(regex)) {
			//调用service逻辑处理层的插入方法,返回布尔类型
			boolean mark=service.insertUser(user);
			//返回提示信息到页面
			if(mark){
				request.setAttribute("info", "用户信息添加成功！！！");
			}else{
				request.setAttribute("info", "该账号已存在，用户信息添加失败！！！");
			}
			//转发到页面(重定向)user_info.jsp提示信息，成功或者失败
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		else {
			System.out.println("不合法");
			request.setAttribute("info", "邮箱不合法，请重新修改");
			request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
		}
		
	}
			
}