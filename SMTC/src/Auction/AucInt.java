package Auction;

import User.Buyer;
import User.User;

//interface pre aukcie
public interface AucInt {
	//default method
	default void setData(User us,String id, String name, String addr, Auction thi) {
		thi.owner = us;
		thi.id = id;
		thi.name= name ;
		thi.address = addr;
	}
}
