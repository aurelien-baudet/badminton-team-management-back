package fr.aba.bad.compo.controller.dto;

public class ErrorDTO {
	private final String type;
	private final String message;
	private final String cause;
	
	public ErrorDTO(String type, String message, String cause) {
		super();
		this.type = type;
		this.message = message;
		this.cause = cause;
	}
	
	public ErrorDTO(String type, String message) {
		this(type, message, null);
	}
	
	public ErrorDTO(Exception e) {
		this(e.getClass().getTypeName(), e.getMessage(), e.getCause()==null ? null : e.getCause().getMessage());
	}

	public String getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public String getCause() {
		return cause;
	}
	
}
