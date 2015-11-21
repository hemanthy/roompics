package com.mobile.exception;

public class MobileException extends Exception {
	private String message = null;

	public MobileException() {
		super();
	}

	public MobileException(String message) {
		super(message);
		this.message = message;
	}

	public MobileException(Throwable cause) {
		super(cause);
	}

	@Override
	public String toString() {
		return message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
