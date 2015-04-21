package com.aurfy.haze.validation;

import java.util.Map;

import com.aurfy.haze.exceptions.ValidationException;

/**
 * @author hermano
 */
public interface Validator {

	/**
	 * <p>
	 * initialize the validator.
	 * </p>
	 *
	 * @param params
	 *            the parameter map
	 * @throws ValidationException
	 *             if initialization failed
	 */
	public void init(Map<String, String> params) throws ValidationException;

	/**
	 * validate the field against the given value (in String format).
	 * 
	 * @param value
	 *            the field value
	 * @return a {@link ValidationError} if validation failed, or the corresponding parsed object (String, Enum,
	 *         Date, Integer, Object, etc) otherwise.
	 */
	public Object validate(String value);

}