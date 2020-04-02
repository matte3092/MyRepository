package com.hermestrade.utility.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hermestrade.utility.library.dto.ErrorResponseDTO;

/**
 * The Class ConflictException.
 */
public class ConflictException extends RuntimeException implements HandledException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5512036866822523227L;

	/** The message. */
	private final String message;

	/**
	 * Instantiates a new conflict exception.
	 *
	 * @param message the message
	 */
	public ConflictException(String message) {
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
				(Object) new ErrorResponseDTO(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.name(), this.message),
				HttpStatus.CONFLICT);
	}

}
