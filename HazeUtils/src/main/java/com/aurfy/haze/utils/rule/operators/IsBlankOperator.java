package com.aurfy.haze.utils.rule.operators;

import static com.aurfy.haze.utils.StringUtils.isBlank;

public class IsBlankOperator extends TextOperator {

	private static final long serialVersionUID = 6526036387846185083L;

	public IsBlankOperator() {
		super();
		setLabel("IS_BLANK");
		setValueRequired(false);
	}

	@Override
	public boolean satisfy(String propertyValue) {
		return isBlank(propertyValue);
	}

}
