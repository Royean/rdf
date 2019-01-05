package beanEntity;

public class Result {
	private int id;
	private int result_ID; 
	private int key_ID; 
	private String key_word;
	private String result; 

	public int getid() {
		return id;
	}
	
	public void setid(int _id) {
		id=_id;
	}
	
	public int getkey_ID() 
	{
		return key_ID;
	}
	
	public void setkey_ID(int key_ID) 
	{
		this.key_ID=key_ID;
	}

	public int getresult_ID() 
	{
		return result_ID;
	}
	
	public void setresult_ID(int result_ID) 
	{
		this.result_ID=result_ID;
	}
	
	public String getkey_word() 
	{
		return key_word;
	}
	
	public void setkey_word(String Key_word) 
	{
		key_word=Key_word;
	}
	
	public String getresult() 
	{
		return result;
	}
	
	public void setresult(String _result) 
	{
		result=_result;
	}
	
}
