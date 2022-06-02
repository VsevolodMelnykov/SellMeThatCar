package Auction;

import User.User;

public class Confirmed extends Accepted {
	protected float time;
	protected boolean shipped;
	
	public Confirmed(User us,String id, String name, String addr) {
		setData(us, id, name, addr, this);
		time = -1;
		shipped = false;
	}
	
	//odmena shippingu
	public void cancelship() {
		time = -1;
		shipped = false;
	}
	
	//ppodtvrdenie na shipping
	public void setorder() {
		time = 15;
		shipped = false;
	}
	
	
	public void shipped() {
		setShipped(true);
	}

	public float getTime() {
		return time;
	}
	
	public void setTime(int ti) {
		if (time > 0) {
			time -= ti; 
		}
		if (time == 0) {
			time = -1;
			shipped();
		}
	}

	public boolean isShipped() {
		return shipped;
	}

	public void setShipped(boolean shipped) {
		this.shipped = shipped;
	}
}
