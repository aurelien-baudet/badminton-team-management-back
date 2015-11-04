package fr.aba.bad.compo.exception;

public class NotificationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8335852687847167764L;

	public NotificationException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotificationException(String message) {
		super(message);
	}

	public NotificationException(Throwable cause) {
		super(cause);
	}

}
