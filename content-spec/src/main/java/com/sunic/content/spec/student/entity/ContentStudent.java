package com.sunic.content.spec.student.entity;

import com.sunic.content.spec.student.facade.sdo.ContentStudentUdo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Pure domain entity for ContentStudent without JPA annotations.
 */
@Getter
@Setter
@Builder
@ToString
public class ContentStudent {
	private Integer id;
	private Integer studentId;
	private Integer contentId;
	private Integer progress;
	private Long registeredTime;
	private Long modifiedTime;

	public void modify(ContentStudentUdo updateSdo) {
		if (updateSdo.getProgress() != null) {
			this.progress = updateSdo.getProgress();
		}
		this.modifiedTime = System.currentTimeMillis();
	}
}
