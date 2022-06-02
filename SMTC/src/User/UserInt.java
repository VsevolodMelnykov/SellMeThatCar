package User;

import User.*;

/**
 * users interface
 * @author Ctdf6
 *
 */
public interface UserInt {
	/**
	 * default method
	 * @param user
	 * @return
	 */
	default String showName(User user) {
		return user.id;
	}
	
	/**
	 * default method
	 * @param login
	 * @param password
	 * @param premi
	 * @param thi
	 */
	default void setData(String login, String password, boolean premi, User thi) {
		thi.id = login;
		thi.pw = password;
		thi.prem = premi;
	}
}
