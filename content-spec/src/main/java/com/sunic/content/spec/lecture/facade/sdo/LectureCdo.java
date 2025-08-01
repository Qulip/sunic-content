package com.sunic.content.spec.lecture.facade.sdo;

import com.sunic.content.spec.lecture.entity.Difficulty;
import com.sunic.content.spec.lecture.entity.LearningType;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

/**
 * Service Data Object for creating Lecture.
 */
@Getter
@Builder
public class LectureCdo {

	@NotNull(message = "Lecture name is required")
	private String name;

	private String description;

	@NotNull(message = "Learning type is required")
	private LearningType learningType;

	@NotNull(message = "Difficulty is required")
	private Difficulty difficulty;

	private String thumbnail;

	@NotNull(message = "Category ID is required")
	private Integer categoryId;

	@NotNull(message = "Registrant is required")
	private Integer registrant;
}