package Auction;

import User.*;

public class Accepted extends Auction {
	protected int abid;
	protected User abidder;
	
	public Accepted(User us,String id, String name, String addr) {
		setData(us, id, name, addr, this);
		abid = 0;
		abidder = null;
	}

	public Accepted() {
		
	}
	
	//odmena accepted aukcie
	public void cancelorder() {
		abid = 0;
		abidder = null;
	}
	
	//akceptovanie aukcie
	public void setorder(int b, User bb) {
		abid = b;
		abidder = bb;
	}
	
	public int getabid() {
		return abid;
	}
	
	public User getabidder() {
		return abidder;
	}
}
