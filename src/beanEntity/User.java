package beanEntity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable 
{
	private int id;
	private String username;
	private String password;
	private String email;
	private String telephone;
	private String activeCode;
	private int role=2;
	private int state;
	private String registTime;
	private String user_login_time;

	
	public String getRegistTime() 
	{
		return registTime;
	}
	
	public String getLoginTime() 
	{
		return user_login_time;
	}

	public void setRegistTime(String registTime) 
	{
		this.registTime = registTime;
	}
	
	public void setLoginTime(String loginTime) 
	{
		this.user_login_time = loginTime;
	}

	public int getState() 
	{
		return state;
	}

	public void setState(int state) 
	{
		this.state = state;
	}
	
	public String getActiveCode() 
	{
		return activeCode;
	}

	public void setActiveCode(String activeCode) 
	{
		this.activeCode = activeCode;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getTelephone() 
	{
		return telephone;
	}

	public void setTelephone(String telephone) 
	{
		this.telephone = telephone;
	}

	public int getRole()
	{
		return role;
	}

	public void setRole(int role)

	{
		this.role = role;
	}

	public String toString() 
	{
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", email=" + email+ ", telephone=" + telephone 
				+ ", role=" + role + "]";
	}
	

}

