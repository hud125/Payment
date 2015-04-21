package com.aurfy.haze.validation;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.exceptions.ValidationException;
import com.aurfy.haze.utils.ResourceUtils;

public class ValidationExecutor {

	private static final Logger logger = LoggerFactory.getLogger(ValidationExecutor.class);

	private RuleMeta meta;

	private ValidationExecutor() {
	}

	private void init(InputStream in) throws ValidationException {
		// parsing xml
		meta = new RuleMeta();
		meta.parse(in);
	}

	public static ValidationExecutor getInstance(String resourceName, ClassLoader loader) throws ValidationException {
		InputStream in = ResourceUtils.getResourceAsStream(resourceName, loader);
		if (in == null) {
			final String msg = "Load validation resource '" + resourceName + "' failed.";
			logger.error(msg);
			throw new ValidationException(msg);
		}
		ValidationExecutor executor = new ValidationExecutor();

		executor.init(in);
		return executor;
	}

	/**
	 * validate all the input parameter, parse and return the result.
	 * 
	 * @param params
	 *            the input parameter map, key is the field name, value is the text to validate and parse (all in String
	 *            format)
	 * @return a map contains all the validation result: key is always the field name, value varies: if validation
	 *         failed, it will be an object of {@link ValidationError}. Otherwise, it will be the parsed result (decided
	 *         by individual validator).
	 */
	public Map<String, Object> validateAll(Map<String, String> params) {
		Map<String, Object> result = new HashMap<String, Object>(CollectionUtils.size(meta.getValidatorMap()));

		// only validates the fields that are listed in the rule file
		String value;
		Object vResult;
		for (Map.Entry<String, Validator> entry : meta.getValidatorMap().entrySet()) {
			value = params.containsKey(entry.getKey()) ? params.get(entry.getKey()) : null;
			vResult = entry.getValue().validate(value);
			result.put(entry.getKey(), vResult);
			if (logger.isTraceEnabled()) {
				logger.trace("{} => {}", entry.getKey(), vResult);
			}
		}

		return result;
	}

}
