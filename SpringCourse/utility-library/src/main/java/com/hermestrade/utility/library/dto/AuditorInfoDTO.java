package com.hermestrade.utility.library.dto;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class BaseEntity.
 */
@MappedSuperclass
@Getter
@Setter
public class AuditorInfoDTO {

	/** The created by. */
	protected String createdBy;

	/** The created date. */
	protected LocalDateTime createdDate;

	/** The last modified by. */
	protected String lastModifiedBy;

	/** The last modified date. */
	protected LocalDateTime lastModifiedDate;
}
