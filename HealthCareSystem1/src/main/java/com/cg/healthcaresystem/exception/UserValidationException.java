package com.cg.healthcaresystem.exception;

public class UserValidationException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserValidationException() {
	}

	public UserValidationException(String string) {
		super(string);
	}
}
