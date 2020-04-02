package com.hermestrade.utility.library.util;

import org.slf4j.helpers.MessageFormatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * The Class SummerUtils.
 */
public class ObjectFormatter {

	/**
	 * Instantiates a new summer utils.
	 */
	private ObjectFormatter() {
		// Nothing.
	}

	/**
	 * As json string.
	 *
	 * @param obj the obj
	 * @return the string
	 * @throws JsonProcessingException the json processing exception
	 */
	public static String asJsonString(Object obj) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return om.writeValueAsString(obj);
	}
	
	/**
	 * Format.
	 *
	 * @param format the format
	 * @param params the params
	 * @return the string
	 */
	public static String format(String format, Object... params) {
		return MessageFormatter.arrayFormat(format, params).getMessage();
	}

}
