package com.sunic.content.spec.lecture.facade.sdo;

import java.util.List;

import com.sunic.content.spec.lecture.entity.Difficulty;
import com.sunic.content.spec.lecture.entity.LearningType;
import com.sunic.content.spec.lecture.entity.LectureState;

import lombok.Builder;
import lombok.Getter;

/**
 * Result Data Object for Lecture retrieval operations.
 */
@Getter
@Builder
public class LectureRdo {
	private final Integer id;
	private final String name;
	private final String description;
	private final LearningType learningType;
	private final Difficulty difficulty;
	private final String thumbnail;
	private final LectureState lectureState;
	private final Integer categoryId;
	private final List<Integer> contentIds;
	private final Long registeredTime;
	private final Integer registrant;
	private final Long modifiedTime;
	private final Integer modifier;
}