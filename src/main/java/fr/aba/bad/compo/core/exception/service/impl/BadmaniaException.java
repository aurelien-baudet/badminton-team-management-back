package fr.aba.bad.compo.core.exception.service.impl;

public class BadmaniaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5734911634693155991L;

	public BadmaniaException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadmaniaException(String message) {
		super(message);
	}

	public BadmaniaException(Throwable cause) {
		super(cause);
	}

}
