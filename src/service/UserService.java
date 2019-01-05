package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.security.auth.login.LoginException;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import java.math.BigInteger;

import dao.UserDao;
import beanEntity.User;
import exception.ActiveUserException;
//import cn.itcast.bookStore.exception.RegisterException;
import utils.MailUtils;

public class UserService 
{
	private UserDao dao = new UserDao();

	public void activate(User user) throws AddressException,SQLException,MessagingException,Exception
	{
		int i = (dao.addUser(user)).intValue();
		// 只有是激活才能登录成功，否则提示“用户未激活”
		user.setId(i);
		System.out.println(i);
		/*// 发送激活邮件
	    String emailMsg = "感谢您注册RDF搜索引擎，点击<a href='http://localhost:8080/registerlogin/activeUser?activeCode="
				+ user.getActiveCode() + "'>&nbsp;激活&nbsp;</a>后使用。<br>为保障您的账户安全，请在24小时内完成激活操作";*/
	    MailUtils.sendMail(user.getEmail(), user.getActiveCode());
    }
	
	//忘记密码发邮件
	public void resetemail(String email,int id) throws Exception
	{
		User user = dao.findUserByemail(email,id);
		if (user == null) {
			throw new ActiveUserException("账号与邮箱不匹配请返回重新输入，请返回重新输入");
		}
		else {
			MailUtils.sendMailreset(email,id);
		}
		/*// 发送激活邮件
	    String emailMsg = "感谢您注册RDF搜索引擎，点击<a href='http://localhost:8080/registerlogin/activeUser?activeCode="
				+ user.getActiveCode() + "'>&nbsp;激活&nbsp;</a>后使用。<br>为保障您的账户安全，请在24小时内完成激活操作";*/
	    //MailUtils.sendMailreset(email,id);
    }
	
	
	
	// 激活用户
	public void activeUser(String activeCode) throws ActiveUserException,ParseException,java.text.ParseException
	{
		try 
		{
			// 根据激活码查找用户
			User user = dao.findUserByActiveCode(activeCode);
			if (user == null) 
			{
				throw new ActiveUserException("激活用户失败,未匹配");
			}

			// 判断激活码是否过期 24小时内激活有效.
			// 1.得到注册时间					
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String registTime = user.getRegistTime();
			try 
			{
			    Date date = formatter.parse(registTime);
			    long time = System.currentTimeMillis() - date.getTime();
			// 2.判断是否超时
			    if (time / 1000 / 60 / 60 > 24) 
			    {
				     throw new ActiveUserException("激活码过期");
			    }
			} 
			catch (ParseException e) 
			{
		        e.printStackTrace();
		    }	
			// 激活用户，就是修改用户的state状态
			dao.active(activeCode);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new ActiveUserException("激活用户失败，sql");
		}
	}
	//验证用户
	public void changePwd(String activeCode) throws ActiveUserException,ParseException,java.text.ParseException,LoginException
	{
		try 
		{		
			// 根据激活码查找用户
			User user = dao.findUserByActiveCode(activeCode);
			if (user == null) 
			{
				throw new ActiveUserException("验证用户失败,未匹配");
			}

			// 判断激活码是否过期 24小时内激活有效.
			// 1.得到注册时间					
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String registTime = user.getRegistTime();
			try 
			{
			    Date date = formatter.parse(registTime);
			    long time = System.currentTimeMillis() - date.getTime();
			    // 2.判断是否超时
			    if (time / 1000 / 60 / 60 > 24) 
			    {
				    throw new ActiveUserException("激活码过期");
			    }
			} 
			catch (ParseException e) 
			{
		        e.printStackTrace();
		    }	
			// 激活用户，/就是修改用户的state状态
			//dao.active2(activeCode);
			user = dao.findUserByactive(user.getId(),user.getActiveCode());
			if (user == null) 
			{
				// 只有是验证才能登录成功，否则提示“用户未激活”
			    throw new LoginException("验证失败");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new LoginException("登录失败");
		}
			
	}

	// 登录操作
	public User login(int id, String password) throws LoginException 
	{
		try 
		{
			//根据登录时表单输入的用户名和密码，查找用户
			User user = dao.findUserByidAndPassword(id, password);
			//如果找到，还需要确定用户是否为激活用户
			if (user != null) 
			{
				// 只有是激活才能登录成功，否则提示“用户未激活”
				if (user.getState() == 1) 
				{
					return user;
				}
				throw new LoginException("用户未激活");
			}
			throw new LoginException("用户名或密码错误");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new LoginException("登录失败");
		}
	}
	////////////////////////////////////////////////////////////////
	//@lixinghao
	//检查密码是否匹配
	public User checkPwd(int id, String password) throws LoginException 
	{
		try 
		{
			//根据登录时表单输入的用户名和密码，查找用户
			User user = dao.findUserByidAndPassword(id, password);
			//如果找到，还需要确定用户是否为激活用户
			
			throw new LoginException("用户名或密码错误");
	    }
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new LoginException("登录失败");
		}
     }
//////////////////////////////////////////////////////////////////////////
	//@tty
	public List<User> selectUser(User user) throws SQLException {
		//使用StringBuffer进行字符串的拼接，不使用String
		//StringBuffer sql=new StringBuffer("select * from user_info where 1=1 ");
		StringBuffer sql=new StringBuffer("select * from user_info where 1=1");
		//设置集合，用户存放用户信息设置值的时候使用
		List<Object> list=null;
		list=new ArrayList<Object>();
		if(user!=null){
			list=new ArrayList<Object>();
			//按照账号查询，如果账号不为null且不为空
			if(user.getId()!=0){
				sql.append(" and id=?");
				list.add(user.getId());
			}
			//按照姓名查询，如果姓名不为null且不为空
			if(user.getUsername()!=null && !user.getUsername().equals("")){
				sql.append(" and username like ?");
				//模糊查询这样拼接字符串
				list.add("%"+user.getUsername()+"%");
			}
			//按照标识查询，如果标识不为null且不为空
			if(user.getRole()!=0){
				sql.append(" and role=?");
				list.add(user.getRole());
			}
		}
		//返回的参数，sql语句是字符类型，集合转化为数组类型
		return dao.selectUser(sql.toString(), list.toArray());
	}
	
	//查找用户
		public User getUser(User user) throws SQLException {
			if(user!=null){
				return dao.findUserByid(user.getId());
			}
			return null;
		}
		
		public boolean updateUser(User user) {
			try{
				if(user!=null &&  String.valueOf(user.getId())!=null){
					//更新的sql语句
					String sql="UPDATE user_info SET username=?,"
							+ "password=?,"
							+ "role=?,email=? WHERE id=?";
					List<Object> list=new ArrayList<Object>();
					//添加到集合中的顺序必须和上面些的字段一致，不然报错
					list.add(user.getUsername());
					list.add(user.getPassword());
					list.add(user.getRole());
					list.add(user.getEmail());
					list.add(user.getId());
					
					//添加和修改（伪删除）都可以调用工具类里面公共的方法。
					UserDao dao = new UserDao();
					int count=dao.addAndUpdate(sql, list.toArray());
					if(count>0){
						return true;
					}else{
						return false;
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return false;
		}
		
		public boolean insertUser(User user) {
			try{
				//System.out.println(user);//测试传来的UserInfo里面是否够存在用户信息
				if(user!=null){
					String sql="INSERT INTO user_info(id,username,"
							+ "password,state,role,email)"
							+ " VALUES(?,?,?,1,?,?)";
					List<Object> list=new ArrayList<Object>();
					//可以理解位将实体类中get到的信息放到数据库中，因为set设置的信息就是为了查到数据库中
					list.add(user.getId());
					list.add(user.getUsername());
					list.add(user.getPassword());
					//list.add(1);//激活
					list.add(user.getRole());
					list.add(user.getEmail());
					//list.add(user.getUserMark());//将设置好的标识信息保存到集合中
					
					//将封装到集合list中的信息和sql语句传递到DbUtils封装好的 方法中
					//这里sql转化位String语句，list转化位数组类型
					UserDao dao = new UserDao();
					int count=dao.addAndUpdate(sql.toString(), list.toArray());
					//System.out.println(count);//测试返回值是0还是1
					if(count>0){
						return true;//成功返回true
					}else{
						return false;//失败返回false
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return false;
		}
	
}
