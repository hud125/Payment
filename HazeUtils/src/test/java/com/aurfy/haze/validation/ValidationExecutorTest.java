package com.aurfy.haze.validation;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.aurfy.haze.conf.HazeDefaultConfig;

public class ValidationExecutorTest {

	private static final String VALIDATION_TEST_RULE_FILE = "validation/test_rule.xml";

	@Test
	public void testValidateAll() throws Exception {
		ValidationExecutor daemon = ValidationExecutor.getInstance(VALIDATION_TEST_RULE_FILE,
				HazeDefaultConfig.class.getClassLoader());
		Map<String, String> map = new HashMap<String, String>();
		// map.put("enumTest", "");
		map.put("currency", "CNY");
		map.put("merId", "M123456789");
		map.put("txnAmount", "123456");
		map.put("browserCallbackUrl", "1111111");
		map.put("serverCallbackUrl", "22222222");
		map.put("orderTime", "fddsfdsf454");
		map.put("userName", "dfq	$434");

		Map<String, Object> result = daemon.validateAll(map);
		for (Map.Entry<String, Object> entry : result.entrySet()) {
			System.out.println(entry.getKey() + "=>" + (entry.getValue() == null ? null : entry.getValue().toString()));
		}

	}
}
