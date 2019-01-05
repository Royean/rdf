package beanEntity;

import java.io.Serializable;
import java.util.Date;

public class Key implements Serializable 
{

	private int KeyId; // 关键词编号
	private int UserId; // 用户编号
	private String KeyWord; // 关键词
	private String InputTime; // 搜索时间

	public int getKeyId() 
	{
		return KeyId;
	}
	
	public void setKeyId(int Key_Id) 
	{
		KeyId=Key_Id;
	}
	
	public int getUserId() 
	{
		return UserId;
	}
	
	public void setUserId(int User_Id) 
	{
		UserId=User_Id;
	}

	public String getKeyWord() 
	{
		return KeyWord;
	}
	
	public void setKeyWord(String Key_Word) 
	{
		KeyWord=Key_Word;
	}
	
	public String getInputTime() 
	{
		return InputTime;
	}
	
	public void setInputTime(String Input_Time) 
	{
		InputTime=Input_Time;
	}
}

