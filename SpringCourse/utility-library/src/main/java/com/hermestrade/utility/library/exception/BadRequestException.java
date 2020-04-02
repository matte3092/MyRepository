package com.hermestrade.utility.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hermestrade.utility.library.dto.ErrorResponseDTO;

/**
 * The Class BadRequestException.
 */
public class BadRequestException extends RuntimeException implements HandledException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5855120368668225237L;

	/** The message. */
	private final String message;

	/**
	 * Instantiates a new bad request exception.
	 *
	 * @param message the message
	 */
	public BadRequestException(String message) {
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
		return new ResponseEntity<>((Object) new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.name(), this.message), HttpStatus.BAD_REQUEST);
	}

}
