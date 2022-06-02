package Auction;

import java.io.Serializable;
import java.util.ArrayList;

import User.*;

public class Auction implements AucInt, Serializable {
	protected User owner;
	protected String id;
	protected String name;
	protected String address;
	public ArrayList<Integer> bid = new ArrayList<>();
	public ArrayList<User> bidder = new ArrayList<>();
	
	public Auction(User us,String id, String name, String addr) {
		setData(us, id, name, addr, this);
	}
	
	public Auction() {
		
	}
	
	//pridanie ceny na aukciu
	public void addbid(int bidd, User bidde) {
		bid.add(bidd);
		bidder.add(bidde);
	}
	
	public User getOwner() {
		return owner;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddr() {
		return address;
	}
	
	public ArrayList getBid() {
		return bid;
	}
	
	public ArrayList getBidder() {
		return bidder;
	}
}
