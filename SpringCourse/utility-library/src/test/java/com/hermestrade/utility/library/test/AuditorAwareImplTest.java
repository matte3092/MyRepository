package com.hermestrade.utility.library.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.AccessToken;
import org.springframework.test.util.ReflectionTestUtils;

import com.hermestrade.utility.library.AuditorAwareImpl;

/**
 * The Class AuditorAwareImplTest.
 */
public class AuditorAwareImplTest {

	/** The auditor aware impl. */
	AuditorAwareImpl auditorAwareImpl = new AuditorAwareImpl();

	/**
	 * Current auditor empty test.
	 */
	@Test
	public void currentAuditorEmptyTest() {
		Assertions.assertTrue(auditorAwareImpl.getCurrentAuditor().isEmpty());
	}

	/**
	 * Current auditor value test.
	 */
	@Test
	public void currentAuditorValueTest() {
		AccessToken accessToken = mock(AccessToken.class);
		when(accessToken.getPreferredUsername()).thenReturn("USER");
		ReflectionTestUtils.setField(auditorAwareImpl, "accessToken", accessToken);
		Assertions.assertFalse(auditorAwareImpl.getCurrentAuditor().isEmpty());
	}
}
