package com.aurfy.haze.utils.rule.operators;

public class IsNullOperator extends TextOperator {

	private static final long serialVersionUID = 8415613690243512381L;

	public IsNullOperator() {
		super();
		setLabel("IS_NULL");
		setValueRequired(false);
	}

	@Override
	public boolean satisfy(String propertyValue) {
		return propertyValue == null;
	}

}
