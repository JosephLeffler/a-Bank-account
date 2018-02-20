package exceptions;

public class NotOwnerOfAccountException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3119509948140579270L;

	public NotOwnerOfAccountException(String Message) {
		super(Message);
	}
}
