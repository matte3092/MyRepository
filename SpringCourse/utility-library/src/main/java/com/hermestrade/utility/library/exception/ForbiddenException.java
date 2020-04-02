package com.hermestrade.utility.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hermestrade.utility.library.dto.ErrorResponseDTO;

/**
 * The Class ForbiddenException.
 */
public class ForbiddenException extends RuntimeException implements HandledException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3686682252375855120L;

	/** The message. */
	private final String message;

	/**
	 * Instantiates a new forbidden exception.
	 *
	 * @param message the message
	 */
	public ForbiddenException(String message) {
		super();
		this.message = message;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the response entity.
	 *
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<Object> getResponseEntity() {
		return new ResponseEntity<>(
				(Object) new ErrorResponseDTO(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.name(), this.message),
				HttpStatus.FORBIDDEN);
	}

}
