package fr.aba.bad.compo.exception;

public class RankingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1822619086135016846L;

	public RankingException(String message, Throwable cause) {
		super(message, cause);
	}

	public RankingException(String message) {
		super(message);
	}

	public RankingException(Throwable cause) {
		super(cause);
	}

}
