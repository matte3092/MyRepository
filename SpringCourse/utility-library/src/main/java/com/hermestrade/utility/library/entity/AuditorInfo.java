package com.hermestrade.utility.library.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class BaseEntity.
 */
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class AuditorInfo {

	/** The created by. */
	@CreatedBy
	@Column(name = "created_by", updatable = false)
	protected String createdBy;

	/** The created date. */
	@CreatedDate
	@Column(name = "created_date", nullable = false, updatable = false)
	protected LocalDateTime createdDate;

	/** The last modified by. */
	@LastModifiedBy
	@Column(name = "last_modified_by")
	protected String lastModifiedBy;

	/** The last modified date. */
	@LastModifiedDate
	@Column(name = "last_modified_date", nullable = false)
	protected LocalDateTime lastModifiedDate;

	/** The version. */
	@Version
	@Column(name = "version", nullable = false)
	private Long version;
}
