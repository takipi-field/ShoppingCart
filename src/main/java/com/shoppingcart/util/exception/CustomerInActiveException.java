package com.shoppingcart.util.exception;

public class CustomerInActiveException extends RuntimeException {

	private static final long serialVersionUID = 3064406863292282746L;

	public CustomerInActiveException() {
		super();
	}

	public CustomerInActiveException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerInActiveException(String message) {
		super(message);
	}

	public CustomerInActiveException(Throwable cause) {
		super(cause);
	}	
}