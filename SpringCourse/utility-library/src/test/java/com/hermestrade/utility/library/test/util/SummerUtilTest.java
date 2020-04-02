package com.hermestrade.utility.library.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hermestrade.utility.library.util.ObjectFormatter;

/**
 * The Class SummerUtilTest.
 */
public class SummerUtilTest {

	/**
	 * As json string test.
	 *
	 * @throws JsonProcessingException the json processing exception
	 */
	@Test
	public void asJsonStringTest() throws JsonProcessingException {
		Map<String, String> obj = new HashMap<>();
		obj.put("key1", "value1");

		String actual = ObjectFormatter.asJsonString(obj);
		String expected = "{\"key1\":\"value1\"}";

		assertEquals(expected, actual);
	}

	/**
	 * Format test.
	 *
	 * @throws JsonProcessingException the json processing exception
	 */
	@Test
	public void formatTest() throws JsonProcessingException {

		String actual = ObjectFormatter.format("TEST: {}={}, {}={}", "keyA", "valueA", "keyB", "valueB");
		String expected = "TEST: keyA=valueA, keyB=valueB";

		Assertions.assertEquals(expected, actual);
	}
}
