package com.hermestrade.utility.library.test.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.hermestrade.utility.library.dto.ErrorResponseDTO;

/**
 * The Class ErrorResponseDTOTest.
 */
public class ErrorResponseDTOTest {

	/**
	 * Test all args constructor.
	 */
	@Test
	public void testAllArgsConstructor() {
		ErrorResponseDTO errorResponse = new ErrorResponseDTO(100, "ERROR", "MESSAGE");

		assertTrue(Integer.valueOf(100).equals(errorResponse.getStatus()));
		assertTrue("MESSAGE".equals(errorResponse.getMessage()));
		assertTrue("ERROR".equals(errorResponse.getError()));
	}

	/**
	 * Test no args constructor.
	 */
	@Test
	public void testNoArgsConstructor() {
		ErrorResponseDTO errorResponse = new ErrorResponseDTO();
		errorResponse.setStatus(100);
		errorResponse.setMessage("MESSAGE");
		errorResponse.setError("ERROR");

		assertTrue(Integer.valueOf(100).equals(errorResponse.getStatus()));
		assertTrue("MESSAGE".equals(errorResponse.getMessage()));
		assertTrue("ERROR".equals(errorResponse.getError()));
	}
}