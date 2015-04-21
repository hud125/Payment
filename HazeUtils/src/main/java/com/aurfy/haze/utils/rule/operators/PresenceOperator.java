package com.aurfy.haze.utils.rule.operators;

import static com.aurfy.haze.utils.ReflectionUtils.invokeGetter;

import com.aurfy.haze.utils.rule.ValueType;
import com.aurfy.haze.utils.rule.exceptions.RuleException;

public class PresenceOperator extends BasicOperator {

	private static final long serialVersionUID = -1129878250248647300L;

	public PresenceOperator() {
		super();
		setLabel("IS_PRESENT");
		setValueRequired(false);
		setValueType(ValueType.Other);
	}

	@SuppressWarnings("unused")
	@Override
	public boolean satisfy(Object object, String propertyName, String valuePattern) throws RuleException {
		try {
			Object propertyValue = invokeGetter(object, propertyName);
			return true;
		} catch (Exception e) {
			return defaultExceptionHandle(e);
		}
	}

}
