package com.hermestrade.utility.library.exception;

import org.springframework.http.ResponseEntity;

/**
 * The Interface HandledException.
 */
public interface HandledException {

	/**
	 * Gets the response entity.
	 *
	 * @return the response entity
	 */
	ResponseEntity<Object> getResponseEntity();

}
