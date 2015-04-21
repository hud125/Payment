package com.aurfy.haze.validation;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import com.aurfy.haze.exceptions.ValidationException;
import com.aurfy.haze.utils.ReflectionUtils;

/**
 * @author hermano
 */
public class PseudoEnumValidator extends BaseValidator implements Validator {

	private Class<?> targetClass;
	private String method;

	public PseudoEnumValidator() {
	}

	@Override
	public void init(Map<String, String> params) throws ValidationException {
		super.init(params);
		// target
		if (!params.containsKey("target")) {
			throw new ValidationException("parameter 'target' is required for " + getSimpleClassName());
		}
		final String className = params.get("target");
		try {
			targetClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new ValidationException("target class '" + className + "' not found for " + getSimpleClassName());
		}
		// method
		method = getParseMethodName(params);
		// parameter types
		Class<?>[] pt = getParseMethodParameterTypes();
		try {
			Method m = ReflectionUtils.getAccessibleMethod(targetClass, method, pt);
			if ((Modifier.STATIC & m.getModifiers()) == 0) {
				throw new ValidationException("method '" + className + "#" + method + "' must be static for "
						+ getSimpleClassName());
			}
		} catch (NoSuchMethodException e) {
			StringBuilder buffer = new StringBuilder();
			for (Class<?> clazz : pt) {
				buffer.append(clazz.getName());
				buffer.append(", ");
			}
			if (pt.length > 1) {
				buffer.delete(buffer.length() - 2, buffer.length());
			}
			throw new ValidationException("method '" + className + "#" + method + "(" + buffer.toString()
					+ ")' not found for " + getSimpleClassName());
		}
	}

	/**
	 * return the static method name for (pseudo) enumeration parsing
	 */
	protected String getParseMethodName(Map<String, String> params) throws ValidationException {
		if (!params.containsKey("method")) {
			throw new ValidationException("parameter 'method' is required for " + getSimpleClassName());
		}
		return params.get("method");
	}

	/**
	 * return the static method parameter types for (pseudo) enumeration parsing
	 * 
	 */
	protected Class<?>[] getParseMethodParameterTypes() {
		// fixed parameter type to String
		return new Class<?>[] { String.class };
	}

	@Override
	protected Object validateNonNull(String value) {
		Object obj = ReflectionUtils.invokeStaticMethod(getTargetClass(), getMethod(), value, String.class);
		if (obj == null) {
			return new ValidationError(getFieldName(), "#{field.enum.notparsable}", new String[] { value });
		}
		return obj;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public String getMethod() {
		return method;
	}

}
