package com.aurfy.haze.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexUtilsTest {

	@Test
	public void testIsValidMonth() {
		assertTrue(RegexUtils.isValidMonth("01"));
		assertTrue(RegexUtils.isValidMonth("03"));
		assertTrue(RegexUtils.isValidMonth("10"));
		assertTrue(RegexUtils.isValidMonth("12"));

		assertFalse(RegexUtils.isValidMonth("00"));
		assertFalse(RegexUtils.isValidMonth("13"));
		assertFalse(RegexUtils.isValidMonth("X1"));
		assertFalse(RegexUtils.isValidMonth("30"));
		assertFalse(RegexUtils.isValidMonth("99"));
		assertFalse(RegexUtils.isValidMonth("88"));
		assertFalse(RegexUtils.isValidMonth("--"));
		assertFalse(RegexUtils.isValidMonth("0010"));
	}

	@Test
	public void testIsValidYearShort() {
		assertTrue(RegexUtils.isValidYearShort("15"));
		assertTrue(RegexUtils.isValidYearShort("16"));
		assertTrue(RegexUtils.isValidYearShort("20"));
		assertTrue(RegexUtils.isValidYearShort("88"));
		assertTrue(RegexUtils.isValidYearShort("99"));

		assertFalse(RegexUtils.isValidYearShort("00"));
		assertFalse(RegexUtils.isValidYearShort("01"));
		assertFalse(RegexUtils.isValidYearShort("09"));
		assertFalse(RegexUtils.isValidYearShort("10"));
		assertFalse(RegexUtils.isValidYearShort("14"));
		assertFalse(RegexUtils.isValidYearShort("--"));
		assertFalse(RegexUtils.isValidYearShort("256"));
		assertFalse(RegexUtils.isValidYearShort("189"));
		assertFalse(RegexUtils.isValidYearShort("2015"));
	}

}
