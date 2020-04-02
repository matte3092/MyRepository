package com.hermestrade.utility.library.test.controller.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hermestrade.utility.library.controller.util.ControllerInterceptorHandler;
import com.hermestrade.utility.library.util.WebConfig;

/**
 * The Class ControllerInterceptorHandlerFalseTest.
 */
@WebAppConfiguration
@ContextConfiguration(classes = { WebConfig.class })
@SpringBootTest
public class ControllerInterceptorHandlerFalseTest {

	/** The request. */
	@Mock
	private HttpServletRequest request;

	/** The response. */
	@Mock
	private HttpServletResponse response;

	/** The handler. */
	@Spy
	@InjectMocks
	private ControllerInterceptorHandler handler = new ControllerInterceptorHandler(false);

	/**
	 * Test pre handle.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testPreHandle() throws Exception {
		Map<String, String[]> params = new HashMap<String, String[]>();
		when(request.getParameterMap()).thenReturn(params);
		assertTrue(handler.preHandle(request, response, null));
		params.put("test", new String[] { "test" });
		when(request.getParameterMap()).thenReturn(params);
		assertTrue(handler.preHandle(request, response, null));
	}

	/**
	 * Test post handle.
	 */
	@Test
	public void testPostHandle() {
		assertDoesNotThrow(() -> {
			handler.postHandle(request, response, null, null);
		});
	}

	/**
	 * Test after completion.
	 */
	@Test
	public void testAfterCompletion() {
		assertDoesNotThrow(() -> {
			handler.afterCompletion(request, response, null, null);
		});
	}
}
