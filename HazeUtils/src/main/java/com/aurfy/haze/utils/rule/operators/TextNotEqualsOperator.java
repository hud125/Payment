package com.aurfy.haze.utils.rule.operators;

public class TextNotEqualsOperator extends TextEqualsOperator {

	private static final long serialVersionUID = -271233357246981602L;

	public TextNotEqualsOperator() {
		super();
		setLabel("NOT_EQUALS");
	}

	@Override
	public boolean satisfy(String propertyValue, String valuePattern) {
		return !super.satisfy(propertyValue, valuePattern);
	}

}
