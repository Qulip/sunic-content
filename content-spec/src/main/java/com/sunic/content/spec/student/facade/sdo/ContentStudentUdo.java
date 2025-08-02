package com.sunic.content.spec.student.facade.sdo;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

/**
 * Update Data Object for ContentStudent modifications.
 */
@Getter
@Builder
public class ContentStudentUdo {

	@NotNull(message = "Content Student ID is required")
	private Integer id;

	private Integer progress;

	@NotNull(message = "Modifier is required")
	private Integer modifier;
}