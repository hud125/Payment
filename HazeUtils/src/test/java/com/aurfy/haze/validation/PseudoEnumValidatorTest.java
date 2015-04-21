package com.aurfy.haze.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.exceptions.ValidationException;

public class PseudoEnumValidatorTest {

	// <rule fieldName="currency" validator="haze.PseudoEnumValidator" target="com.aurfy.haze.core.model.Currency"
	// method="parseByName" nullable="false" />

	private static final String CURRENCY_CLASSNAME = Currency.class.getName() /* "com.aurfy.haze.core.model.Currency" */;

	@Test
	public void testInitParams() {
		PseudoEnumValidator validator = new PseudoEnumValidator();
		Map<String, String> map = new HashMap<String, String>();
		map.put("fieldName", "currency");
		map.put("nullable", "false");

		try {
			validator.init(map);
			fail("should throw exception");
		} catch (ValidationException e) {
			// OK
		}

		map.put("target", CURRENCY_CLASSNAME);
		try {
			validator.init(map);
			fail("should throw exception");
		} catch (ValidationException e) {
			// OK
		}

		map.put("method", "parseByName");
		try {
			validator.init(map);
		} catch (ValidationException e) {
			e.printStackTrace();
			fail("should not throw exception");
		}

	}

	@Test
	public void testWrongClassName() {
		PseudoEnumValidator validator = new PseudoEnumValidator();
		Map<String, String> map = new HashMap<String, String>();
		map.put("fieldName", "currency");
		map.put("nullable", "false");
		map.put("target", CURRENCY_CLASSNAME + "111");
		map.put("method", "parseByName");
		try {
			validator.init(map);
			fail("should throw exception");
		} catch (ValidationException e) {
			// OK
		}
	}

	@Test
	public void testMethodName() {
		PseudoEnumValidator validator = new PseudoEnumValidator();
		Map<String, String> map = new HashMap<String, String>();
		map.put("fieldName", "currency");
		map.put("nullable", "false");
		map.put("target", CURRENCY_CLASSNAME);
		map.put("method", "parseByName1");
		try {
			validator.init(map);
			fail("should throw exception");
		} catch (ValidationException e) {
			// OK
		}
		map.put("method", "parseByName");
		try {
			validator.init(map);
		} catch (ValidationException e) {
			fail("should not throw exception");
		}
	}

	@Test
	public void testNonStaticMethodName() {
		PseudoEnumValidator validator = new PseudoEnumValidator();
		Map<String, String> map = new HashMap<String, String>();
		map.put("fieldName", "currency");
		map.put("nullable", "false");
		map.put("target", CURRENCY_CLASSNAME);
		map.put("method", "toString");
		try {
			validator.init(map);
			fail("should throw exception");
		} catch (ValidationException e) {
			e.printStackTrace();
			// OK
		}
	}

	@Test
	public void testValidate() {
		PseudoEnumValidator validator = new PseudoEnumValidator();
		Map<String, String> map = new HashMap<String, String>();
		map.put("fieldName", "currency");
		map.put("nullable", "false");
		map.put("target", CURRENCY_CLASSNAME);
		map.put("method", "parseByName");
		try {
			validator.init(map);
			validator.validate("CNY");
			Currency c = (Currency) validator.validate("CNY");
			assertEquals(c, Currency.CNY);
		} catch (Exception e) {
			fail("should not throw exception");
		}
		// error case
		try {
			validator.init(map);
			Object obj = validator.validate("CNY1");
			assertTrue(obj instanceof ValidationError);
			System.out.println(obj.toString());
		} catch (Exception e) {
			fail("should not throw exception");
		}
	}

}
