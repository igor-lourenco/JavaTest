package com.frete.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
