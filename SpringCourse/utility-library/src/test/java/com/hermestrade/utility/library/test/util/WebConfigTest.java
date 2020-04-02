package com.hermestrade.utility.library.test.util;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.hermestrade.utility.library.util.WebConfig;

/**
 * The Class WebConfigTest.
 */
public class WebConfigTest {

	/** The web config. */
	WebConfig webConfig = new WebConfig();

	/**
	 * Adds the interceptors test.
	 */
	@Test
	public void addInterceptorsTest() {
		Assertions.assertDoesNotThrow(() -> {
			InterceptorRegistry registry = mock(InterceptorRegistry.class);
			webConfig.addInterceptors(registry);
		});
	}

	/**
	 * Adds the cors mappings test.
	 */
	@Test
	public void addCorsMappingsTest() {
		Assertions.assertDoesNotThrow(() -> {
			CorsRegistry registry = mock(CorsRegistry.class);
			when(registry.addMapping(any())).thenReturn(mock(CorsRegistration.class));
			webConfig.addCorsMappings(registry);
		});
	}
}
