package com.aurfy.haze.utils.rule.operators;

public class BeginWithOperator extends TextOperator {

	private static final long serialVersionUID = -7120846927673797227L;

	public BeginWithOperator() {
		super();
		setLabel("BEGIN_WITH");
	}

	@Override
	protected boolean satisfy(String propertyValue, String valuePattern) {
		return propertyValue != null && propertyValue.startsWith(valuePattern);
	}

}
