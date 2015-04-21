package com.aurfy.haze.service.exceptions;

public class DuplicateException extends ServiceException {

	private static final long serialVersionUID = -5983000787955080732L;

	public DuplicateException() {
	}

	public DuplicateException(String message) {
		super(message);
	}

	public DuplicateException(Throwable cause) {
		super(cause);
	}

	public DuplicateException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
