package com.shoppingcart.exception;

public class ShoppingCartException extends RuntimeException {

	private static final long serialVersionUID = 3064406863292282746L;

	public ShoppingCartException() {
		super();
	}

	public ShoppingCartException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShoppingCartException(String message) {
		super(message);
	}

	public ShoppingCartException(Throwable cause) {
		super(cause);
	}	
}