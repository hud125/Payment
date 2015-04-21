package com.aurfy.haze.dao.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DBWildcardUtilsTest {

	private static final char[] inputs = new char[] { '%', '_', '?', '#', '&', '|', '\\', '/', '.', '\'', '"', '*', 'v' };
	private static final String[] outputs = new String[] { "\\%", "\\_", "\\?", "\\#", "\\&", "\\|", "\\\\", "\\/",
			".", "'", "\"", "*", "v" };

	@Test
	public void testEscapeWildcards() {
		String result;
		for (int i = 0; i < inputs.length; i++) {
			System.out.print("replacing " + inputs[i] + " ==> ");
			result = DBWildcardUtils.escapeWildcards(String.valueOf(inputs[i]));
			assertEquals(outputs[i], result);
			System.out.println(result);
		}
	}

}
