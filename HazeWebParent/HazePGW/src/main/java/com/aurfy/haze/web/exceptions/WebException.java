package com.aurfy.haze.web.exceptions;

public class WebException extends Exception {

	private static final long serialVersionUID = -5560903436646144869L;

	public WebException() {
	}

	public WebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WebException(String message, Throwable cause) {
		super(message, cause);
	}

	public WebException(String message) {
		super(message);
	}

	public WebException(Throwable cause) {
		super(cause);
	}

}
