package com.sunic.content.spec.student.facade.sdo;

import lombok.Builder;
import lombok.Getter;

/**
 * Query Data Object for LectureStudent search operations.
 */
@Getter
@Builder
public class LectureStudentQdo {
	private final Integer studentId;
	private final Integer lectureId;
}