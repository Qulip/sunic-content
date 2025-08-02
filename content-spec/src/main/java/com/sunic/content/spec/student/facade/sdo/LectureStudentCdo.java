package com.sunic.content.spec.student.facade.sdo;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

/**
 * Service Data Object for creating LectureStudent.
 */
@Getter
@Builder
public class LectureStudentCdo {

	@NotNull(message = "Student ID is required")
	private Integer studentId;

	@NotNull(message = "Lecture ID is required")
	private Integer lectureId;

	@NotNull(message = "Registrant is required")
	private Integer registrant;
}