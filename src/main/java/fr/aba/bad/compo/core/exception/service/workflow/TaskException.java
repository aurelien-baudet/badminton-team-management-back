package fr.aba.bad.compo.core.exception.service.workflow;

public class TaskException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4589887270168652036L;

	public TaskException(String message, Throwable cause) {
		super(message, cause);
	}

	public TaskException(String message) {
		super(message);
	}

	public TaskException(Throwable cause) {
		super(cause);
	}

}
