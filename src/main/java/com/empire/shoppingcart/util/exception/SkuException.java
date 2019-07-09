package com.empire.shoppingcart.util.exception;

public class SkuException extends RuntimeException {

	private static final long serialVersionUID = 3064406863292282746L;

	public SkuException() {
		super();
	}

	public SkuException(String message, Throwable cause) {
		super(message, cause);
	}

	public SkuException(String message) {
		super(message);
	}

	public SkuException(Throwable cause) {
		super(cause);
	}	
}