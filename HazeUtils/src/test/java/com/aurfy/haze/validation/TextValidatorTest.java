package com.aurfy.haze.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.aurfy.haze.exceptions.ValidationException;
import com.aurfy.haze.validation.TextValidator;

public class TextValidatorTest {

	// <rule fieldName="merId" validator="haze.TextValidator" minLength="8" maxLength="32" nullable="false" />

	@Test
	public void testInitFieldName() {
		TextValidator validator = new TextValidator();
		Map<String, String> map = new HashMap<String, String>();
		try {
			validator.init(map);
			fail("should throw exception");
		} catch (ValidationException e) {
			// OK
		}
		map.put("fieldName", "textField");
		try {
			validator.init(map);
			assertEquals("textField", validator.getFieldName());
		} catch (ValidationException e) {
			fail("should not throw exception");
		}
	}

	@Test
	public void testInitNullable() {
		TextValidator validator = new TextValidator();
		Map<String, String> map = new HashMap<String, String>();
		map.put("fieldName", "textField");

		map.put("nullable", "0");
		try {
			validator.init(map);
			assertFalse(validator.isNullable());
		} catch (ValidationException e) {
			fail("should not throw exception");
		}
		map.put("nullable", "yes");
		try {
			validator.init(map);
			assertFalse(validator.isNullable());
		} catch (ValidationException e) {
			fail("should not throw exception");
		}
		map.put("nullable", "false");
		try {
			validator.init(map);
			assertFalse(validator.isNullable());
		} catch (ValidationException e) {
			fail("should not throw exception");
		}
		map.put("nullable", "true");
		try {
			validator.init(map);
			assertTrue(validator.isNullable());
		} catch (ValidationException e) {
			fail("should not throw exception");
		}
	}

	@Test
	public void testInitLength() {
		TextValidator validator = new TextValidator();
		Map<String, String> map = new HashMap<String, String>();
		map.put("fieldName", "textField");

		map.put("minLength", "abc");
		try {
			validator.init(map);
			fail("should throw exception");
		} catch (ValidationException e) {
			// OK
		}

		map.remove("minLength");
		map.put("maxLength", "-1");
		try {
			validator.init(map);
			fail("should throw exception");
		} catch (ValidationException e) {
			// OK
		}
	}

}
