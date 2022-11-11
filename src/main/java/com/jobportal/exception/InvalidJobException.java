package com.jobportal.exception;
public class InvalidJobException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidJobException() {
		super();
	}

	public InvalidJobException(String message) {
		super(message);
	}

}
