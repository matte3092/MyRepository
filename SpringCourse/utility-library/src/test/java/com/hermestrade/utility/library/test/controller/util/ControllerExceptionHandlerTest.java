package com.hermestrade.utility.library.test.controller.util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.hermestrade.utility.library.controller.util.ControllerExceptionHandler;
import com.hermestrade.utility.library.exception.NotFoundException;

/**
 * The Class ControllerExceptionHandlerTest.
 */
public class ControllerExceptionHandlerTest {

	/**
	 * Handled exception test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void HandledExceptionTest() throws Exception {
		ControllerExceptionHandler ceh = new ControllerExceptionHandler();
		assertTrue(ceh.handleException(new NotFoundException("TEST"), null).getClass().equals(ResponseEntity.class));
	}

	/**
	 * Illegal argument exception test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void IllegalArgumentExceptionTest() throws Exception {
		ControllerExceptionHandler ceh = new ControllerExceptionHandler();
		assertTrue(ceh.handleException(new IllegalArgumentException("TEST"), null).getClass()
				.equals(ResponseEntity.class));
	}

	/**
	 * Constraint violation exception test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void ConstraintViolationExceptionTest() throws Exception {
		ControllerExceptionHandler ceh = new ControllerExceptionHandler();
		assertTrue(ceh.handleException(new ConstraintViolationException(null), null).getClass()
				.equals(ResponseEntity.class));
	}

	/**
	 * Invalid data access api usage exception test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void InvalidDataAccessApiUsageExceptionTest() throws Exception {
		ControllerExceptionHandler ceh = new ControllerExceptionHandler();
		assertTrue(ceh.handleException(new InvalidDataAccessApiUsageException("TEST"), null).getClass()
				.equals(ResponseEntity.class));
	}

	/**
	 * Method argument not valid exception exception test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void MethodArgumentNotValidExceptionExceptionTest() throws Exception {
		MethodParameter methodParam = mock(MethodParameter.class);
		BindingResult bindingResult = mock(BindingResult.class);
		List<ObjectError> errorsList = Collections
				.<ObjectError>singletonList(new FieldError("someObj", "someField", "TEST"));
		when(bindingResult.getAllErrors()).thenReturn(errorsList);

		ControllerExceptionHandler ceh = new ControllerExceptionHandler();
		assertTrue(ceh.handleException(new MethodArgumentNotValidException(methodParam, bindingResult), null).getClass()
				.equals(ResponseEntity.class));
	}

	/**
	 * Unhandled exception test.
	 */
	@Test
	public void UnhandledExceptionTest() {

		assertThrows(RuntimeException.class, () -> {
			ControllerExceptionHandler ceh = new ControllerExceptionHandler();
			ceh.handleException(new RuntimeException(), null);

		});

	}
}
