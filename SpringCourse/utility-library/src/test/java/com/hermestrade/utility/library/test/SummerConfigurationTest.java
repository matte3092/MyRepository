package com.hermestrade.utility.library.test;

import static org.mockito.Mockito.spy;

import java.security.Principal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hermestrade.utility.library.KeycloakSecurityConfiguration;

/**
 * The Class SummerConfigurationTest.
 */
public class SummerConfigurationTest {

	/**
	 * The Class KeyCloackMockRequest.
	 */
	public static class KeyCloackMockRequest extends MockHttpServletRequest {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.springframework.mock.web.MockHttpServletRequest#getUserPrincipal()
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public Principal getUserPrincipal() {
			return new KeycloakPrincipal(null, new KeycloakSecurityContext());
		}

	}

	/**
	 * Test security.
	 */
	@Test
	public void testSecurity() {
		Assertions.assertDoesNotThrow(() -> {
			KeycloakSecurityConfiguration mainClass = spy(new KeycloakSecurityConfiguration());
			MockHttpServletRequest request = new KeyCloackMockRequest();
			RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
			Mockito.when(mainClass.getRequest()).thenReturn(request);
			mainClass.getKeycloakSecurityContext();
			mainClass.getAccessToken();
		});
	}

	/**
	 * Auditor aware test.
	 */
	@Test
	public void auditorAwareTest() {
		Assertions.assertDoesNotThrow(() -> {
			KeycloakSecurityConfiguration mainClass = spy(new KeycloakSecurityConfiguration());
			Assertions.assertNotNull(mainClass.auditorAware());
		});
	}

}