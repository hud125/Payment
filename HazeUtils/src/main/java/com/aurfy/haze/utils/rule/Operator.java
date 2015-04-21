package com.aurfy.haze.utils.rule;

import java.io.Serializable;

import com.aurfy.haze.utils.rule.exceptions.RuleException;

public interface Operator extends Serializable {

	String getLabel();

	ValueType getValueType();

	boolean isValueRequired();

	boolean satisfy(Object object, String propertyName, String valuePattern) throws RuleException;

}
