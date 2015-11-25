package fr.aba.bad.compo.core.exception.player.provider;

public class PlayerInfoNotFoundException extends PlayerProviderException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7097303941375617787L;

	private final String licence;
	
	public PlayerInfoNotFoundException(String message, String licence, Throwable cause) {
		super(message, cause);
		this.licence = licence;
	}

	public PlayerInfoNotFoundException(String message, String licence) {
		super(message);
		this.licence = licence;
	}

	public PlayerInfoNotFoundException(String licence, Throwable cause) {
		super(cause);
		this.licence = licence;
	}

	public String getLicence() {
		return licence;
	}
}
