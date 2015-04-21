package com.aurfy.haze.validation;

import java.util.Arrays;

/**
 * @author hermano
 */
public class ValidationError {

	private String fieldName;
	private String errorKey;
	private Object[] params;

	public ValidationError() {

	}

	/**
	 * Stands for a field validation error, without any runtime parameter values.
	 */
	public ValidationError(String fieldName, String errorKey) {
		this(fieldName, errorKey, null);
	}

	/**
	 * Stands for a field validation error.
	 * 
	 * @param fieldName
	 *            the field's name be checked
	 * @param errorKey
	 *            the error message key (for i18n purpose) associated with this validation error
	 * @param params
	 *            an array contains all the runtime parameter values for better error message support
	 */
	public ValidationError(String fieldName, String errorKey, Object[] params) {
		super();
		this.fieldName = fieldName;
		this.errorKey = errorKey;
		this.params = params;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ValidationError [fieldName=");
		builder.append(fieldName);
		builder.append(", errorKey=");
		builder.append(errorKey);
		builder.append(", params=");
		builder.append(Arrays.toString(params));
		builder.append("]");
		return builder.toString();
	}

}