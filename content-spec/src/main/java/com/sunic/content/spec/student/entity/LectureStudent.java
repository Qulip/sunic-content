package com.sunic.content.spec.student.entity;

import java.util.List;

import com.sunic.content.spec.student.facade.sdo.LectureStudentCdo;
import com.sunic.content.spec.student.facade.sdo.LectureStudentRdo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Pure domain entity for LectureStudent without JPA annotations.
 */
@Getter
@Setter
@Builder
@ToString
public class LectureStudent {
	private Integer id;
	private Integer studentId;
	private Integer lectureId;
	private List<ContentStudent> contentStudents;
	private Long registeredTime;
	private Long modifiedTime;

	public static LectureStudent create(LectureStudentCdo createSdo) {
		return LectureStudent.builder()
			.studentId(createSdo.getStudentId())
			.lectureId(createSdo.getLectureId())
			.registeredTime(System.currentTimeMillis())
			.modifiedTime(System.currentTimeMillis())
			.build();
	}

	public LectureStudentRdo toRdo() {
		return LectureStudentRdo.builder()
			.id(this.id)
			.studentId(this.studentId)
			.lectureId(this.lectureId)
			.contentStudents(this.contentStudents)
			.registeredTime(this.registeredTime)
			.modifiedTime(this.modifiedTime)
			.build();
	}
}
