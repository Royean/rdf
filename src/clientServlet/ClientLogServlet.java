package clientServlet;

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
 * Servlet implementation class ClientLogServlet
 */
public class ClientLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientLogServlet() {
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
		Result result=new Result();
		ResultDao dao=new ResultDao();
		String key=null;
		String Result=null;
		try {
			//List<Result> list = service.selectResult(result);
			List<Result> list = new ArrayList<Result>();
			if(request.getParameter("key").equals("")&&request.getParameter("result").equals("")) {
				System.out.println("搜索框为空");
				list=dao.selectResult2();
			}
			else if(!(request.getParameter("key")).equals("")) {
				//System.out.println(request.getParameter("id"));
				key=new String(request.getParameter("key").getBytes("ISO8859_1"),"utf-8");
				System.out.println("key:"+key);
				if(!(request.getParameter("result")).equals("")) {
					System.out.println("!!!"+request.getParameter("result").toString());
					Result=new String(request.getParameter("result").getBytes("ISO8859_1"),"utf-8");
					//int id=Integer.parseInt((String) (request.getSession().getAttribute("userid")));
					int id=Integer.valueOf(request.getSession().getAttribute("userid").toString()).intValue();
					list=dao.SelectByKeyResult(key, Result,id);
				}else {
					System.out.println("id不会空，name为空");
					int id=Integer.valueOf(request.getSession().getAttribute("userid").toString()).intValue();
					list=dao.SelectByKey(key,id);
				}
			}
			else if(!(request.getParameter("result")).equals("")) {
				Result=new String(request.getParameter("result").getBytes("ISO8859_1"),"utf-8");
				System.out.println("name"+request.getParameter("name"));
				int id=Integer.valueOf(request.getSession().getAttribute("userid").toString()).intValue();
				list=dao.SelectByResult(Result,id);
			}
			System.out.println("list:"+list.toString());
			JSONArray arr=JsonUtils.formatRsToJsonArray(list,Result.class);
			System.out.println("session设置成功"+arr.toString());
			request.getSession().setAttribute("result", arr);
			
			//request.setAttribute("nlist", list);
			//request.setAttribute("result", result);
			request.getRequestDispatcher("/client/querylog.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
