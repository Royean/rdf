package dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
//import org.json.JSONArray;
//import org.json.JSONObject;
import beanEntity.Result;
import beanEntity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.DataSourceUtils;
import utils.JsonUtils;

public class ResultDao {
	public static Connection conn;
	public static PreparedStatement pstat;
	public static String sql="";
	
	
	// 添加查询记录
	public void addResult(Result result) throws SQLException 
	{
		QueryRunner runner=new QueryRunner();
		Connection conn=DataSourceUtils.getConnection();
		String sql = "insert into result_info(key_ID,result) values(?,?);";
		runner.update(conn, sql, new Object[]{result.getkey_ID(),result.getresult()});
	}
	
	//根据关键词查找查询日志
	public static List<Result> SelectByKey(String key,int id) throws SQLException
	{
		QueryRunner runner = new QueryRunner();
		conn = (Connection)DataSourceUtils.getConnection();
		sql = "select k.id,r.result_ID,r.key_ID,k.key_word,r.result from result_info as r,key_info as k,user_info AS u where k.key_ID=r.key_ID and k.id=u.id and k.key_word like ? and k.id=?";
		return runner.query(conn,sql, new BeanListHandler<Result>(Result.class),"%"+key+"%",id);
	}
	
	//根据关键词和结果查找查询日志
		public static List<Result> SelectByKeyResult(String key,String result,int id) throws SQLException
		{
			System.out.println(id);
			QueryRunner runner = new QueryRunner();
			conn = (Connection)DataSourceUtils.getConnection();
			sql = "select k.id,r.result_ID,r.key_ID,k.key_word,r.result from result_info as r,key_info as k,user_info AS u where k.key_ID=r.key_ID and k.id=u.id and k.key_word like ? and r.result like ? and k.id=?";
			return runner.query(conn,sql, new BeanListHandler<Result>(Result.class),"%"+key+"%","%"+result+"%",id);
		}
	
		//根据结果查找查询日志
				public static List<Result> SelectByResult(String result,int id) throws SQLException
				{
					QueryRunner runner = new QueryRunner();
					conn = (Connection)DataSourceUtils.getConnection();
					sql = "select k.id,r.result_ID,r.key_ID,k.key_word,r.result from result_info as r,key_info as k,user_info AS u where k.key_ID=r.key_ID and k.id=u.id and r.result like ? and k.id=?";
					return runner.query(conn,sql, new BeanListHandler<Result>(Result.class),"%"+result+"%",id);
				}
		
	//根据用户id查找查询日志
		public static List<Result> SelectById(int id) throws SQLException
		{
			QueryRunner runner = new QueryRunner();
			conn = (Connection)DataSourceUtils.getConnection();
			sql = "select k.id,r.result_ID,r.key_ID,k.key_word,r.result from result_info as r,key_info as k,user_info AS u where k.key_ID=r.key_ID and k.id=u.id and k.id=?";
			return runner.query(conn,sql, new BeanListHandler<Result>(Result.class),id);
		}
	
	//根据用户姓名,id查找查询日志
	public static List<Result> SelectByNameId(int id,String name) throws SQLException
	{
		QueryRunner runner = new QueryRunner();
		conn = (Connection)DataSourceUtils.getConnection();
		sql = "select k.id,r.result_ID,r.key_ID,k.key_word,r.result from result_info as r,key_info as k,user_info AS u where k.key_ID=r.key_ID and k.id=u.id and k.id=? and u.username like ?";
		return runner.query(conn,sql, new BeanListHandler<Result>(Result.class),id,"%"+name+"%");
	}
	
	//根据用户姓名查找查询日志
	public static List<Result> SelectByName(String name) throws SQLException
	{
		QueryRunner runner = new QueryRunner();
		conn = (Connection)DataSourceUtils.getConnection();
		sql = "select k.id,r.result_ID,r.key_ID,k.key_word,r.result from result_info as r,key_info as k,user_info AS u where k.key_ID=r.key_ID and k.id=u.id and u.username like ?";
		return runner.query(conn,sql, new BeanListHandler<Result>(Result.class),"%"+name+"%");
	}
	
	
	//普通用户根据关键词id显示查询日志
	public static List<Result> selectResult1(int id) throws SQLException
	{
		QueryRunner runner = new QueryRunner();
		conn = (Connection)DataSourceUtils.getConnection();
		sql = "select k.id,r.result_ID,r.key_ID,k.key_word,r.result from result_info as r,key_info as k where k.key_ID=r.key_ID and k.id=?";
		return runner.query(conn,sql, new BeanListHandler<Result>(Result.class),id);
	}
	
	//管理员显示查询日志
	public static List<Result> selectResult2() throws SQLException
	{
		QueryRunner runner = new QueryRunner();
		conn = (Connection)DataSourceUtils.getConnection();
		sql = "select k.id,r.result_ID,r.key_ID,k.key_word,r.result from result_info as r,key_info as k where k.key_ID=r.key_ID";
		return runner.query(conn,sql, new BeanListHandler<Result>(Result.class));
	}
	
	public static JSONArray returnResult1(int id) throws SQLException
	{
		List<Result> list = new ArrayList<Result>();
		list= selectResult1(id);
		//System.out.println(list);
		//System.out.println("=1=【BeanListHandler】dao返回的数据：\n"+list);
		JSONArray arr=JsonUtils.formatRsToJsonArray(list,Result.class);
		//JSONArray arr = new JSONArray(list);
		System.out.println(arr.toString());
		return arr;
	}
	
	public static JSONArray returnResult2() throws SQLException
	{
		List<Result> list = new ArrayList<Result>();
		list= selectResult2();
		//System.out.println(list);
		//System.out.println("=1=【BeanListHandler】dao返回的数据：\n"+list);
		JSONArray arr=JsonUtils.formatRsToJsonArray(list,Result.class);
		//JSONArray arr = new JSONArray(list);
		System.out.println(arr.toString());
		return arr;
	}
	
	public static void main(String args[]) {
		try {
			//returnResult1(10000);
			//returnResult2();
			List<Result> list = new ArrayList<Result>();
			//list= SelectByNameId(10000, "xiahao");
			//list=selectResult1(10000);
			list= SelectByName("xiahao");
			JSONArray arr=JsonUtils.formatRsToJsonArray(list,Result.class);
			System.out.println(arr.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
