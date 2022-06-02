package User;

import java.io.Serializable;

public class User implements Serializable, UserInt{
	protected String id;
	protected String pw;
	protected boolean prem = false;
	
	public User(String login, String password, boolean premi) {
		setData(login, password, premi, this);
	}
	
	public User(String login, String password) {
		id = login;
		pw = password;
	}
	
	public User() {
		
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public boolean getPrem()
	{
		return prem;
	}
	
}
