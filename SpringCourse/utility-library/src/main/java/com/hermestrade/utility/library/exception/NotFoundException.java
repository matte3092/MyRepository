package com.hermestrade.utility.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hermestrade.utility.library.dto.ErrorResponseDTO;

/**
 * The Class NotFoundException.
 */
public class NotFoundException extends RuntimeException implements HandledException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5855168225237203686L;

	/** The message. */
	private final String message;

	/**
	 * Instantiates a new not found exception.
	 *
	 * @param message the message
	 */
	public NotFoundException(String message) {
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
				(Object) new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), this.message),
				HttpStatus.NOT_FOUND);
	}

}
