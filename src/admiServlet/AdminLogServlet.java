package admiServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.JsonUtils;
import beanEntity.Result;
import dao.ResultDao;

/**
 * Servlet implementation class AdminLogServlet
 */
public class AdminLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		boolean ifDispatch=false;
		Result result=new Result();
		ResultDao dao=new ResultDao();
		int id=Integer.valueOf(request.getSession().getAttribute("userid").toString()).intValue();
		String name=null;
		try {
			//List<Result> list = service.selectResult(result);
			List<Result> list = new ArrayList<Result>();
			if(request.getParameter("id").equals("")&&request.getParameter("name").equals("")) {
				System.out.println("搜索框为空");
				list=dao.selectResult2();
			}
			else if(!(request.getParameter("id")).equals("")) {
				//System.out.println(request.getParameter("id"));
				String regex1 = "\\d{5}";
				System.out.println("id  "+id);
				if(!request.getParameter("id").matches(regex1)) {
					System.out.println("账号必须为5位数，请重新添加");
					ifDispatch=true;
					request.setAttribute("info", "账号必须为5位数，请重新添加");
					request.getRequestDispatcher("/admin/id_error.jsp").forward(request, response);
				}
				else {
					id=Integer.valueOf(new String(request.getParameter("id").getBytes("ISO8859_1"),"utf-8")).intValue();
					System.out.println("id  "+id);
					System.out.println("id:"+id);
				    if(!(request.getParameter("name")).equals("")) {
					    System.out.println("!!!"+request.getParameter("name").toString());
					    name=new String(request.getParameter("name").getBytes("ISO8859_1"),"utf-8");
					    list=dao.SelectByNameId(id, name);
				    }else {
					    System.out.println("id不会空，name为空");
					    list=dao.SelectById(id);
				    }
				}
				
			}
			else if(!(request.getParameter("name")).equals("")) {
				name=new String(request.getParameter("name").getBytes("ISO8859_1"),"utf-8");
				System.out.println("name"+request.getParameter("name"));
				list=dao.SelectByName(name);
			}
			System.out.println("list:"+list.toString());
			JSONArray arr=JsonUtils.formatRsToJsonArray(list,Result.class);
			System.out.println("session设置成功"+arr.toString());
			if(ifDispatch==false) {
				request.getSession().setAttribute("result", arr);
				
				//request.setAttribute("nlist", list);
				//request.setAttribute("result", result);
				request.getRequestDispatcher("/admin/querylog.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
