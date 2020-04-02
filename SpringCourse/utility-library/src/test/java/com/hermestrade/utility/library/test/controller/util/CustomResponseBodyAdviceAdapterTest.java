package com.hermestrade.utility.library.test.controller.util;

import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.test.util.ReflectionTestUtils;

import com.hermestrade.utility.library.controller.util.CustomResponseBodyAdviceAdapter;

/**
 * The Class CustomResponseBodyAdviceAdapterTest.
 */
public class CustomResponseBodyAdviceAdapterTest {

	/** The custom response body advice adapter. */
	CustomResponseBodyAdviceAdapter customResponseBodyAdviceAdapter = new CustomResponseBodyAdviceAdapter();

	/**
	 * Setup.
	 */
	@BeforeEach
	public void setup() {
		/** Setting fields */
		ReflectionTestUtils.setField(customResponseBodyAdviceAdapter, "controllersEndpointLogEnable", true);
		ReflectionTestUtils.setField(customResponseBodyAdviceAdapter, "controllersBodyLogEnable", true);
	}

	/**
	 * Before body write catch json processing exception test.
	 */
	@Test
	public void beforeBodyWriteCatchJsonProcessingExceptionTest() {
		Assertions.assertDoesNotThrow(() -> {
			Map<String, Object> body = new HashMap<>();
			body.put(null, null);
			ServletServerHttpRequest request = new ServletServerHttpRequest(mock(HttpServletRequest.class));
			ServletServerHttpResponse response = new ServletServerHttpResponse(mock(HttpServletResponse.class));
			customResponseBodyAdviceAdapter.beforeBodyWrite(body, null, null, null, request, response);
		});

	}

	/**
	 * Supports test.
	 */
	@Test
	public void supportsTest() {
		Assertions.assertTrue(customResponseBodyAdviceAdapter.supports(null, null));
	}

	/**
	 * Before body write test.
	 */
	@Test
	public void beforeBodyWriteTest() {
		Map<String, Object> body = new HashMap<>();
		body.put("a", "b");
		ServletServerHttpRequest request = new ServletServerHttpRequest(mock(HttpServletRequest.class));
		ServletServerHttpResponse response = new ServletServerHttpResponse(mock(HttpServletResponse.class));
		Object returnedBody = customResponseBodyAdviceAdapter.beforeBodyWrite(body, null, null, null, request,
				response);
		Assertions.assertEquals(body, returnedBody);

	}
}
