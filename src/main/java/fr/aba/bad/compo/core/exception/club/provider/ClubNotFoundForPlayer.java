package fr.aba.bad.compo.core.exception.club.provider;

public class ClubNotFoundForPlayer extends ClubProviderException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5397870722548093116L;

	public ClubNotFoundForPlayer(String message, Throwable cause) {
		super(message, cause);
	}

	public ClubNotFoundForPlayer(String message) {
		super(message);
	}

	public ClubNotFoundForPlayer(Throwable cause) {
		super(cause);
	}

}
