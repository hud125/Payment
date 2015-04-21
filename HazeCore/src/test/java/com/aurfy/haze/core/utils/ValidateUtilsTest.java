package com.aurfy.haze.core.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class ValidateUtilsTest {

	@Test
	public void testToZeroInCurrentDay() {
		try {
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			Date d1 = ValidateUtils.toZeroInCurrentDay(df.parse("20150304163402"));
			Date d2 = ValidateUtils.toZeroInCurrentDay(df.parse("20150304000000"));
			Date d3 = ValidateUtils.toZeroInCurrentDay(df.parse("20150304235959"));
			Date d4 = ValidateUtils.toZeroInCurrentDay(df.parse("20150304240000"));
			assertEquals(d1, d2);
			assertEquals(d1, d3);
			assertNotEquals(d1, d4);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
