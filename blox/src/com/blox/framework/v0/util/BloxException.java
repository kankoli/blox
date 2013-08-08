package com.blox.framework.v0.util;

public class BloxException extends Exception {
	private static final long serialVersionUID = 1L;

	public BloxException(String message) {
		super(message);
	}
	
	public BloxException(Throwable cause) {
		super(cause);
	}
	
	public BloxException(String message, Throwable cause) {
		super(message, cause);
	}
}
