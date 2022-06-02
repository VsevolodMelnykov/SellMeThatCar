package User;

import java.util.ArrayList;
import java.util.List;

import Auction.Auction;

public class Seller extends Buyer {	
	protected ArrayList<Auction> auct = new ArrayList<>();
	protected ArrayList<Integer> bid = new ArrayList<>();
	
	public Seller(String login, String password, boolean premi) {
		setData(login, password, premi, this);
	}
	
	public Seller profile() {
		return this;
	}
	
	/**
	 * zapamatanie ceny ktoru navrhol pouzivatel
	 * @param bidd
	 * @param auc
	 */
	public void addauction(int bidd, Auction auc)
	{
		this.auct.add(auc);
		this.bid.add(bidd);
	}
	
	/**
	 * odmena svojej ceny
	 * @param bidd
	 * @param auc
	 */
	public void removebid(int bidd, Auction auc) {
		for (int i = 0; i < auct.size(); i++){
			Auction a = auct.get(i);
			int b = bid.get(i);
			if (a == auc && b == bidd)
			{
				auct.remove(i);
				bid.remove(i);
				break;
			}
		}
	}
	
	/**
	 * return zoznam bidov pre aukciu
	 * @return
	 */
	public List[] seebids() {
		List[] j = null;
		j[0] = auct;
		j[1] = bid;
		return j;
	}
	
}
