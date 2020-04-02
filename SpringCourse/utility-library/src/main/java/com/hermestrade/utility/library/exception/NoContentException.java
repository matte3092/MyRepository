package com.hermestrade.utility.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hermestrade.utility.library.dto.ErrorResponseDTO;

/**
 * The Class NoContentException.
 */
public class NoContentException extends RuntimeException implements HandledException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3626692252075855120L;

	/** The message. */
	private final String message;

	/**
	 * Instantiates a new no content exception.
	 *
	 * @param message the message
	 */
	public NoContentException(String message) {
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
		return new ResponseEntity<>((Object) new ErrorResponseDTO(HttpStatus.NO_CONTENT.value(),
				HttpStatus.NO_CONTENT.name(), this.message), HttpStatus.NO_CONTENT);
	}

}
