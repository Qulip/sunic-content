package com.sunic.content.spec.student.entity;

import java.util.List;

import com.sunic.content.spec.student.facade.sdo.LectureStudentCdo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Pure domain entity for LectureStudent without JPA annotations.
 */
@Getter
@Builder
@ToString
public class LectureStudent {
	private final Integer id;
	private final Integer studentId;
	private final Integer lectureId;
	private final List<ContentStudent> contentStudents;
	private final Long registeredTime;
	private final Long modifiedTime;

	public static LectureStudent create(LectureStudentCdo createSdo) {
		return LectureStudent.builder()
			.studentId(createSdo.getStudentId())
			.lectureId(createSdo.getLectureId())
			.registeredTime(System.currentTimeMillis())
			.modifiedTime(System.currentTimeMillis())
			.build();
	}
}
