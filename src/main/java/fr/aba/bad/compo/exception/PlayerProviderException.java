package fr.aba.bad.compo.exception;

public class PlayerProviderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8443999724477852586L;

	public PlayerProviderException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlayerProviderException(String message) {
		super(message);
	}

	public PlayerProviderException(Throwable cause) {
		super(cause);
	}

}
