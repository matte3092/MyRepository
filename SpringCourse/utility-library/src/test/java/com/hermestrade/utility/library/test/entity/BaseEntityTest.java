package com.hermestrade.utility.library.test.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.hermestrade.utility.library.dto.AuditorInfoDTO;

/**
 * The Class BaseDTOTest.
 */
public class BaseEntityTest {

	/**
	 * Test no args constructor.
	 */
	@Test
	public void testNoArgsConstructor() {
		AuditorInfoDTO baseDTO = new AuditorInfoDTO();
		baseDTO.setCreatedBy("CREATED_BY");
		baseDTO.setLastModifiedBy("MODIFIED_BY");
		baseDTO.setCreatedDate(LocalDateTime.MIN);
		baseDTO.setLastModifiedDate(LocalDateTime.MIN);

		assertTrue("MODIFIED_BY".equals(baseDTO.getLastModifiedBy()));
		assertTrue("CREATED_BY".equals(baseDTO.getCreatedBy()));
		assertTrue(LocalDateTime.MIN.equals(baseDTO.getCreatedDate()));
		assertTrue(LocalDateTime.MIN.equals(baseDTO.getLastModifiedDate()));
	}
}