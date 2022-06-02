package Exceptions;

/**
 * custom exception
 * @author Ctdf6
 *
 */
public class IncorrectUserException extends Exception{
	public IncorrectUserException(String em){
		super(em);
	}
}