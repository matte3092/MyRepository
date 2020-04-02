package com.hermestrade.utility.library.test.exception;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.hermestrade.utility.library.dto.ErrorResponseDTO;
import com.hermestrade.utility.library.exception.BadRequestException;
import com.hermestrade.utility.library.exception.ConflictException;
import com.hermestrade.utility.library.exception.ForbiddenException;
import com.hermestrade.utility.library.exception.InternalServerErrorException;
import com.hermestrade.utility.library.exception.NoContentException;
import com.hermestrade.utility.library.exception.NotAcceptableException;
import com.hermestrade.utility.library.exception.NotFoundException;

/**
 * The Class HandledExceptionTest.
 */
public class HandledExceptionTest {

	/**
	 * Bad request exception test.
	 */
	@Test
	public void badRequestExceptionTest() {
		String message = "BAD_REQUEST";
		int status = 400;

		BadRequestException exc = new BadRequestException(message);
		assertTrue(message.equals(exc.getMessage()));

		ResponseEntity<Object> responseEntity = exc.getResponseEntity();
		assertNotNull(responseEntity);

		assertTrue(ErrorResponseDTO.class.isInstance(responseEntity.getBody()));

		ErrorResponseDTO dto = (ErrorResponseDTO) responseEntity.getBody();
		assertEquals(responseEntity.getStatusCodeValue(), dto.getStatus().intValue());
		assertEquals(responseEntity.getStatusCodeValue(), status);
		assertEquals(message, dto.getMessage());
		assertEquals(message, dto.getError());
	}

	/**
	 * Conflict exception test.
	 */
	@Test
	public void conflictExceptionTest() {
		String message = "CONFLICT";
		int status = 409;

		ConflictException exc = new ConflictException(message);
		assertTrue(message.equals(exc.getMessage()));

		ResponseEntity<Object> responseEntity = exc.getResponseEntity();
		assertNotNull(responseEntity);

		assertTrue(ErrorResponseDTO.class.isInstance(responseEntity.getBody()));

		ErrorResponseDTO dto = (ErrorResponseDTO) responseEntity.getBody();
		assertEquals(responseEntity.getStatusCodeValue(), dto.getStatus().intValue());
		assertEquals(responseEntity.getStatusCodeValue(), status);
		assertEquals(message, dto.getMessage());
		assertEquals(message, dto.getError());
	}

	/**
	 * Forbidden exception test.
	 */
	@Test
	public void forbiddenExceptionTest() {
		String message = "FORBIDDEN";
		int status = 403;

		ForbiddenException exc = new ForbiddenException(message);
		assertTrue(message.equals(exc.getMessage()));

		ResponseEntity<Object> responseEntity = exc.getResponseEntity();
		assertNotNull(responseEntity);

		assertTrue(ErrorResponseDTO.class.isInstance(responseEntity.getBody()));

		ErrorResponseDTO dto = (ErrorResponseDTO) responseEntity.getBody();
		assertEquals(responseEntity.getStatusCodeValue(), dto.getStatus().intValue());
		assertEquals(responseEntity.getStatusCodeValue(), status);
		assertEquals(message, dto.getMessage());
		assertEquals(message, dto.getError());
	}

	/**
	 * Not found exception test.
	 */
	@Test
	public void notFoundExceptionTest() {
		String message = "NOT_FOUND";
		int status = 404;

		NotFoundException exc = new NotFoundException(message);
		assertTrue(message.equals(exc.getMessage()));

		ResponseEntity<Object> responseEntity = exc.getResponseEntity();
		assertNotNull(responseEntity);

		assertTrue(ErrorResponseDTO.class.isInstance(responseEntity.getBody()));

		ErrorResponseDTO dto = (ErrorResponseDTO) responseEntity.getBody();
		assertEquals(responseEntity.getStatusCodeValue(), dto.getStatus().intValue());
		assertEquals(responseEntity.getStatusCodeValue(), status);
		assertEquals(message, dto.getMessage());
		assertEquals(message, dto.getError());
	}

	/**
	 * No content exception test.
	 */
	@Test
	public void noContentExceptionTest() {
		String message = "NO_CONTENT";
		int status = 204;

		NoContentException exc = new NoContentException(message);
		assertTrue(message.equals(exc.getMessage()));

		ResponseEntity<Object> responseEntity = exc.getResponseEntity();
		assertNotNull(responseEntity);

		assertTrue(ErrorResponseDTO.class.isInstance(responseEntity.getBody()));

		ErrorResponseDTO dto = (ErrorResponseDTO) responseEntity.getBody();
		assertEquals(responseEntity.getStatusCodeValue(), dto.getStatus().intValue());
		assertEquals(responseEntity.getStatusCodeValue(), status);
		assertEquals(message, dto.getMessage());
		assertEquals(message, dto.getError());
	}

	/**
	 * Not acceptable exception test.
	 */
	@Test
	public void notAcceptableExceptionTest() {
		String message = "NOT_ACCEPTABLE";
		int status = 406;

		NotAcceptableException exc = new NotAcceptableException(message);
		assertTrue(message.equals(exc.getMessage()));

		ResponseEntity<Object> responseEntity = exc.getResponseEntity();
		assertNotNull(responseEntity);

		assertTrue(ErrorResponseDTO.class.isInstance(responseEntity.getBody()));

		ErrorResponseDTO dto = (ErrorResponseDTO) responseEntity.getBody();
		assertEquals(responseEntity.getStatusCodeValue(), dto.getStatus().intValue());
		assertEquals(responseEntity.getStatusCodeValue(), status);
		assertEquals(message, dto.getMessage());
		assertEquals(message, dto.getError());
	}

	/**
	 * Internal Server Error exception test.
	 */
	@Test
	public void internalServerErrorExceptionTest() {
		String message = "INTERNAL_SERVER_ERROR";
		int status = 500;

		InternalServerErrorException exc = new InternalServerErrorException(message);
		assertTrue(message.equals(exc.getMessage()));

		ResponseEntity<Object> responseEntity = exc.getResponseEntity();
		assertNotNull(responseEntity);

		assertTrue(ErrorResponseDTO.class.isInstance(responseEntity.getBody()));

		ErrorResponseDTO dto = (ErrorResponseDTO) responseEntity.getBody();
		assertEquals(responseEntity.getStatusCodeValue(), dto.getStatus().intValue());
		assertEquals(responseEntity.getStatusCodeValue(), status);
		assertEquals(message, dto.getMessage());
		assertEquals(message, dto.getError());
	}
}
