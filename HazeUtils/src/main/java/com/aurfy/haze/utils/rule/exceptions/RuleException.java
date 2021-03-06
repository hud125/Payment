package com.aurfy.haze.utils.rule.exceptions;

public class RuleException extends Exception {

	private static final long serialVersionUID = 2434992304559801439L;

	public RuleException() {
		super();
	}

	public RuleException(String message, Throwable cause) {
		super(message, cause);
	}

	public RuleException(String message) {
		super(message);
	}

	public RuleException(Throwable cause) {
		super(cause);
	}

}
