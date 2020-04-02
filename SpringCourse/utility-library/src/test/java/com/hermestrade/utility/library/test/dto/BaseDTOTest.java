package com.hermestrade.utility.library.test.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.hermestrade.utility.library.entity.AuditorInfo;

/**
 * The Class BaseDTOTest.
 */
public class BaseDTOTest {

	/**
	 * Test no args constructor.
	 */
	@Test
	public void testNoArgsConstructor() {
		AuditorInfo baseEntity = new AuditorInfo();
		baseEntity.setCreatedBy("CREATED_BY");
		baseEntity.setLastModifiedBy("MODIFIED_BY");
		baseEntity.setCreatedDate(LocalDateTime.MIN);
		baseEntity.setLastModifiedDate(LocalDateTime.MIN);
		baseEntity.setVersion(1L);

		assertTrue("MODIFIED_BY".equals(baseEntity.getLastModifiedBy()));
		assertTrue("CREATED_BY".equals(baseEntity.getCreatedBy()));
		assertTrue(LocalDateTime.MIN.equals(baseEntity.getCreatedDate()));
		assertTrue(LocalDateTime.MIN.equals(baseEntity.getLastModifiedDate()));
		assertTrue(Long.valueOf(1L).equals(baseEntity.getVersion()));

	}
}