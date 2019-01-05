package admiServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beanEntity.*;
import dao.UserDao;
import service.UserService;
import com.my.web.servlet.RequestBeanUtils;
import java.util.List; 

public class UserinfoselectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//将表单提交的数据封装到javabean的实体类中
		User user=RequestBeanUtils.requestToSimpleBean(request, User.class);
		//调用service业务逻辑层的代码
		UserService service=new UserService();
        //String gid = String.valueOf(user.getId());
        //String regex = "\\d{5}";
		String gid = request.getParameter("id");
        String regex = "[0-9][0-9][0-9][0-9][0-9]";
		//调用业务逻辑层的查询方法selectUser，将返回的集合接受
		if(gid==null || gid.equals("") || gid.matches(regex) || gid.equals("0")) {
			try {
				List<User> list = service.selectUser(user);
				//将上面的集合和user对象设置在域中，方便userinfo_list.jsp调用
				request.setAttribute("nlist", list);
				request.setAttribute("user", user);
				//将转发到userinfo_list.jsp这个页面
				request.getRequestDispatcher("admin/user_list.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			request.setAttribute("info", "账号必须为5位数");
			request.getRequestDispatcher("/admin/id_warning.jsp").forward(request, response);
		}
	}

}
