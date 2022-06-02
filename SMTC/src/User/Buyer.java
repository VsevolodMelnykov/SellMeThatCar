package User;

import java.util.ArrayList;

import Auction.Auction;

public class Buyer extends User {
	protected ArrayList<Auction> auction = new ArrayList<>();

	public Buyer(String login, String password, boolean premi) {
		setData(login, password, premi, this);
	}
	
	public Buyer() {
		
	}
	
	public Buyer profile() {
		return this;
	}
	
	/**
	 * pridavanie aukcii ktoru vlastni pouzivatel
	 * @param auc
	 */
	public void addauction(Auction auc)
	{
		auction.add(auc);
	}
	
	/**
	 * zmazanie aukcii
	 * @param auc
	 */
	public void deleteauction(Auction auc) {
		for (int i = 0; i < auction.size(); i++){
			Auction a = auction.get(i);
			if (a == auc)
			{
				auction.remove(i);
				break;
			}
		}
	}
	
	public ArrayList<Auction> getauctions(){
		return auction;
	}
	
}
