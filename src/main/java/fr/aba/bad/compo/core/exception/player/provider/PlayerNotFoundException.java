package fr.aba.bad.compo.core.exception.player.provider;

public class PlayerNotFoundException extends PlayerProviderException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7097303941375617787L;

	private final String licence;
	
	public PlayerNotFoundException(String message, String licence, Throwable cause) {
		super(message, cause);
		this.licence = licence;
	}

	public PlayerNotFoundException(String message, String licence) {
		super(message);
		this.licence = licence;
	}

	public PlayerNotFoundException(String licence, Throwable cause) {
		super(cause);
		this.licence = licence;
	}

	public String getLicence() {
		return licence;
	}
}
