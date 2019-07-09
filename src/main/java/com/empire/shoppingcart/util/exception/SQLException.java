package com.empire.shoppingcart.util.exception;

public class SQLException extends RuntimeException {

	private static final long serialVersionUID = 3064406863292282746L;

	public SQLException() {
		super();
	}

	public SQLException(String message, Throwable cause) {
		super(message, cause);
	}

	public SQLException(String message) {
		super(message);
	}

	public SQLException(Throwable cause) {
		super(cause);
	}	
}