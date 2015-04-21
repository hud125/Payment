package com.aurfy.haze.utils.rule.operators;

public class TextNotInOperator extends TextInOperator {

	private static final long serialVersionUID = 1391823583317309074L;

	public TextNotInOperator() {
		super();
		setLabel("NOT_IN");
	}

	@Override
	public boolean satisfy(String propertyValue, String valuePattern) {
		return !super.satisfy(propertyValue, valuePattern);
	}
}
