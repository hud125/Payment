package com.aurfy.haze.utils.rule.operators;

import com.aurfy.haze.utils.rule.Operator;
import com.aurfy.haze.utils.rule.ValueType;
import com.aurfy.haze.utils.rule.exceptions.RuleException;

public abstract class BasicOperator implements Operator {

	private static final long serialVersionUID = 249519499360724576L;
	
	private String label;
	private ValueType valueType;
	private boolean valueRequired;

	public BasicOperator() {
		super();
		this.valueRequired = true;
		this.valueType = ValueType.Other;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public ValueType getValueType() {
		return valueType;
	}

	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}

	public boolean isValueRequired() {
		return valueRequired;
	}

	public void setValueRequired(boolean valueRequired) {
		this.valueRequired = valueRequired;
	}

	protected boolean defaultExceptionHandle(Exception e) throws RuleException {
		if (RuleException.class.isInstance(e)) {
			throw RuleException.class.cast(e);
		}
		return false;
	}

}
