package com.hermestrade.utility.library.controller.util;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.hermestrade.utility.library.exception.BadRequestException;
import com.hermestrade.utility.library.exception.ConflictException;
import com.hermestrade.utility.library.exception.ForbiddenException;
import com.hermestrade.utility.library.exception.HandledException;
import com.hermestrade.utility.library.exception.InternalServerErrorException;
import com.hermestrade.utility.library.exception.NoContentException;
import com.hermestrade.utility.library.exception.NotAcceptableException;
import com.hermestrade.utility.library.exception.NotFoundException;

/**
 * The Class ControllerExceptionHandler.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	/**
	 * Handle exception.
	 *
	 * @param exception the exception
	 * @param request   the request
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@ExceptionHandler({ BadRequestException.class, ForbiddenException.class, ConflictException.class,
			NotFoundException.class, NoContentException.class, NotAcceptableException.class,
			InternalServerErrorException.class, Exception.class })
	public final ResponseEntity<Object> handleException(Exception exception, WebRequest request) throws Exception {
		if (exception instanceof HandledException) {
			return ((HandledException) exception).getResponseEntity();
		} else if (exception instanceof IllegalArgumentException || exception instanceof ConstraintViolationException
				|| exception instanceof InvalidDataAccessApiUsageException) {
			// Handling constraints validations
			return new BadRequestException(exception.getMessage()).getResponseEntity();
		} else if (exception instanceof MethodArgumentNotValidException) {
			// When raising an exception using @Valid annotation.
			StringBuilder stringBuilder = new StringBuilder();
			for (ObjectError objectError : ((MethodArgumentNotValidException) exception).getBindingResult()
					.getAllErrors()) {
				stringBuilder.append(objectError.getDefaultMessage() + "; ");
			}
			return new BadRequestException(stringBuilder.toString().trim()).getResponseEntity();
		}
		throw exception;
	}

}
