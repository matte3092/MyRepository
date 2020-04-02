package com.hermestrade.utility.library.test.controller.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.hermestrade.utility.library.controller.util.CustomRequestBodyAdviceAdapter;

/**
 * The Class CustomRequestBodyAdviceAdapterTest.
 */
public class CustomRequestBodyAdviceAdapterTest {

	/** The custom request body advice adapter. */
	CustomRequestBodyAdviceAdapter customRequestBodyAdviceAdapter = new CustomRequestBodyAdviceAdapter();

	/**
	 * Setup.
	 */
	@BeforeEach
	public void setup() {
		/** Setting fields */
		ReflectionTestUtils.setField(customRequestBodyAdviceAdapter, "controllersEndpointLogEnable", true);
		ReflectionTestUtils.setField(customRequestBodyAdviceAdapter, "controllersBodyLogEnable", true);
	}

	/**
	 * After body read catch json processing exception test.
	 */
	@Test
	public void afterBodyReadCatchJsonProcessingExceptionTest() {
		Assertions.assertDoesNotThrow(() -> {
			Map<String, Object> body = new HashMap<>();
			body.put(null, null);
			customRequestBodyAdviceAdapter.afterBodyRead(body, null, null, null, null);
		});

	}

	/**
	 * Supports test.
	 */
	@Test
	public void supportsTest() {
		Assertions.assertTrue(customRequestBodyAdviceAdapter.supports(null, null, null));
	}

	/**
	 * After body read test.
	 */
	@Test
	public void afterBodyReadTest() {
		Assertions.assertDoesNotThrow(() -> {
			Map<String, Object> body = new HashMap<>();
			body.put("a", "b");
			customRequestBodyAdviceAdapter.afterBodyRead(body, null, null, null, null);
		});
	}
}
