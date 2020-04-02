package com.hermestrade.utility.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class ErrorResponseDTO.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {

	/** The status. */
	private Integer status;

	/** The error. */
	private String error;

	/** The message. */
	private String message;

}