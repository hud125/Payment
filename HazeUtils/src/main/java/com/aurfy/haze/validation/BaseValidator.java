package com.aurfy.haze.validation;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.aurfy.haze.exceptions.ValidationException;

/**
 * @author hermano
 */
public abstract class BaseValidator implements Validator {

	private String fieldName;
	private boolean nullable = false;

	public BaseValidator() {
	}

	protected String getSimpleClassName() {
		return getClass().getSimpleName();
	}

	@Override
	public void init(Map<String, String> params) throws ValidationException {
		if (MapUtils.isEmpty(params)) {
			throw new ValidationException("missing init parameters for " + getSimpleClassName());
		}
		fieldName = params.get("fieldName");
		if (StringUtils.isBlank(fieldName)) {
			throw new ValidationException("'fieldName' can not be blank for " + getSimpleClassName());
		}
		String nullableStr = params.get("nullable");
		if (StringUtils.isNotEmpty(nullableStr)) {
			nullable = Boolean.parseBoolean(nullableStr);
		}
	}

	@Override
	public Object validate(String value) {
		if (isNullable()) {
			if (value == null) {
				return null;
			} else {
				return this.validateNonNull(value);
			}
		} else {
			if (value == null) {
				return new ValidationError(getFieldName(), "#{field.null}");
			} else {
				return this.validateNonNull(value);
			}
		}
	}

	/**
	 * subclasses need to implement the validation logic for non-null value (do NOT need to handle null case)
	 * 
	 */
	protected abstract Object validateNonNull(String value);

	public String getFieldName() {
		return fieldName;
	}

	public boolean isNullable() {
		return nullable;
	}

}
