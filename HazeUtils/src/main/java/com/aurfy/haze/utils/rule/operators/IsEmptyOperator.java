package com.aurfy.haze.utils.rule.operators;

import static com.aurfy.haze.utils.StringUtils.isEmpty;

public class IsEmptyOperator extends TextOperator {

	private static final long serialVersionUID = -7935258741496904073L;

	public IsEmptyOperator() {
		super();
		setLabel("IS_EMPTY");
		setValueRequired(false);
	}

	@Override
	public boolean satisfy(String propertyValue) {
		return isEmpty(propertyValue);
	}

}
