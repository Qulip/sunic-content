package com.sunic.content.spec.student.facade.sdo;

import java.util.List;

import com.sunic.content.spec.student.entity.ContentStudent;

import lombok.Builder;
import lombok.Getter;

/**
 * Result Data Object for LectureStudent retrieval operations.
 */
@Getter
@Builder
public class LectureStudentRdo {
	private final Integer id;
	private final Integer studentId;
	private final Integer lectureId;
	private final List<ContentStudent> contentStudents;
	private final Long registeredTime;
	private final Long modifiedTime;
}