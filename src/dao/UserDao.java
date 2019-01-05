package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;

import java.sql.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
//import com.mysql.jdbc.*;

import beanEntity.User;
import utils.MailUtils;
import utils.C3p0Utils;
import utils.DataSourceUtils;

public class UserDao 
{
	public static Connection conn;
	public static PreparedStatement pstat;
	public static String sql="";
	
	
	public BigInteger addUser(User user) throws SQLException 
	{	
		QueryRunner qr = new QueryRunner();
		conn = (Connection)DataSourceUtils.getConnection();
		sql = "insert into user_info(username,password,email,registTime,activeCode) values (?,?,?,?,?);";
		qr.update(conn,sql, new Object[]{user.getUsername(),user.getPassword(),user.getEmail(),user.getRegistTime(),user.getActiveCode()});			
		//BigInteger id = BigInteger.ZERO ;
		return (BigInteger)qr.query(conn, "SELECT LAST_INSERT_ID()", new ScalarHandler(1)); 
	}
	
	
	public User findUserByActiveCode(String activeCode) throws SQLException 
	{
		sql = "select * from user_info where activeCode=?";
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), activeCode);
	}
		
	public int findidByActiveCode(String activeCode) throws SQLException 
	{
		sql = "select * from user_info where activeCode=?";
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		return runner.query(conn, sql, new ScalarHandler<Integer>(),activeCode); 
	}
	
	
	public void active(String activeCode) throws SQLException 
	{
		String sql = "update user_info set state=? where activeCode=?";
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		runner.update(sql, 1, activeCode);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	* 
	*忘记密码
	*/
	public User findUserByemail(String email,int id) throws SQLException
	{
	   QueryRunner runner = new QueryRunner();
	   conn = (Connection)DataSourceUtils.getConnection();
	   sql = "select * from user_info where email=? and id=?";
	   return runner.query(conn,sql, new BeanHandler<User>(User.class),email,id);
	}
	
	public void changePassword(int id,String pwd) throws SQLException
	{
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
	   conn = (Connection)DataSourceUtils.getConnection();
	   sql = "update user_info set password=? where id=?";
       runner.update(sql,pwd,id);
	}	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	* 
	* 登录
	*/
	public void updateLogintime(String t,int id) throws SQLException 
	{	
	   QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
	   conn = (Connection)DataSourceUtils.getConnection();
	   sql = "update user_info set user_login_time=? where id=?";
	   runner.update(sql,t,id);	
	}
	
	public User findUserByidAndPassword(int id,String pwd) throws SQLException
	{
	   QueryRunner runner = new QueryRunner();
	   conn = (Connection)DataSourceUtils.getConnection();
	   sql = "select * from user_info where id=? and password=?";
	   return runner.query(conn,sql, new BeanHandler<User>(User.class),id,pwd);
	}		
	
	
	public User findUserByactive(int id,String activecode) throws SQLException
	{
	   QueryRunner runner = new QueryRunner();
	   conn = (Connection)DataSourceUtils.getConnection();
	   sql = "select * from user_info where id=? and activeCode=?";
	   return runner.query(conn,sql, new BeanHandler<User>(User.class),id,activecode);
	}
	
	/**
	* 
	*权限更改
	*/
	public User findUserByid(int id) throws SQLException
	{
	   QueryRunner runner = new QueryRunner();
	   conn = (Connection)DataSourceUtils.getConnection();
	   sql = "select * from user_info where id=?";
	   return runner.query(conn,sql, new BeanHandler<User>(User.class),id);
	}
//////////////////////////////////////////////////////////////
	/////@lixinghao
/*public String pwd(int id) throws SQLException {   

		//通过传过来的参数，来查询密码是多少

		String sql = "select USER_PWD from task_user where ID=?";

		Connection conn = null;

		PreparedStatement stmt = null;

		conn = getConnection();

		stmt = conn.prepareStatement(sql);

		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();

		//定义一个空的字符串

		String pwdd = "";

		//从结果集中获取值放到pwdd中

		if (rs.next()) {

			pwdd = rs.getString(1);

		}

		//返回数据库中查到的密码

		return pwdd;

	}
*/
/*
private Connection getConnection() {
	// TODO Auto-generated method stub
	return null;
}*/
//////////////////////////////////////////////////////////////////////////////
//@tty
	/***
	 * 添加(插入)和更新(更改)可以提取公共的方法写在工具类中
	 * 删除一般使用伪删除，这样删除就是更新(更改)操作，
	 * 所以只有查询(查找)需要写更多的代码
	 * @param sql 外面传来的sql语句
	 * @param arr 外面传来的数组类型的，是用户信息封装到集合传递进来
	 * @return 返回的是一个整形的数据类型
	 */
	public static int addAndUpdate(String sql,Object[] arr){
		PreparedStatement ps=null;
		try{
			conn = (Connection)DataSourceUtils.getConnection();
			ps=conn.prepareStatement(sql);//第二步预编译
			//第三步给sql语句中的参数复制
			for(int i=0;i<arr.length;i++){
				ps.setObject(i+1, arr[i]);
			}
			//第四步执行sql并且返回。
			return ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}/*finally{
			conn.close();
		}*/
		return 0;
	}
	
	public List<User> selectUser(String sql,Object[] arr) throws SQLException{
		conn = (Connection)DataSourceUtils.getConnection();
		PreparedStatement ps=null;
		List<User> list = null;
		// 获取数据库连接
		try{
			ps=conn.prepareStatement(sql);//第二步预编译
			//第三步给sql语句中的参数复制
			if(arr!=null && arr.length>0){
				for(int i=0;i<arr.length;i++){
					ps.setObject(i+1, arr[i]);
				}
			}
			//第四步执行sql并且返回。
			ResultSet rs = ps.executeQuery();
			// 光标向后移动，并判断是否有效
			list=new ArrayList<User>();
			while (rs.next()) {
				// 实例化user
				User p = new User();
				p.setId(rs.getInt("id"));
				p.setUsername(rs.getString("username"));
				p.setPassword(rs.getString("password"));
				p.setRegistTime(rs.getString("registTime"));
				p.setLoginTime(rs.getString("user_login_time"));
				p.setEmail(rs.getString("email"));
				p.setRole(rs.getInt("role"));
				list.add(p);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}/*finally{
			conn.close();
		}*/
		return null;
	}
	/////////////////////////////////////////////////////////////////////////////////
	
	
}