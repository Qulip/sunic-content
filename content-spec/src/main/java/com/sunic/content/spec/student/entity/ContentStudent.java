package com.sunic.content.spec.student.entity;

import com.sunic.content.spec.student.facade.sdo.ContentStudentUdo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Pure domain entity for ContentStudent without JPA annotations.
 */
@Getter
@Builder
@ToString
public class ContentStudent {
	private final Integer id;
	private final Integer studentId;
	private final Integer contentId;
	private final Integer progress;
	private final Long registeredTime;
	private final Long modifiedTime;

	public ContentStudent modify(ContentStudentUdo updateSdo) {
		return ContentStudent.builder()
			.id(this.id)
			.studentId(this.studentId)
			.contentId(this.contentId)
			.progress(updateSdo.getProgress() != null ? updateSdo.getProgress() : this.progress)
			.registeredTime(this.registeredTime)
			.modifiedTime(System.currentTimeMillis())
			.build();
	}
}
