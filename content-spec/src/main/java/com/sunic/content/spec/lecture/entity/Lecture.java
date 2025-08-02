package com.sunic.content.spec.lecture.entity;

import java.util.ArrayList;
import java.util.List;

import com.sunic.content.spec.lecture.facade.sdo.LectureCdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureRdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureUdo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Pure domain entity for Lecture without JPA annotations.
 */
@Getter
@Setter
@Builder
@ToString
public class Lecture {
	private Integer id;
	private String name;
	private String description;
	private LearningType learningType;
	private LectureState lectureState;
	private String thumbnail;
	private Difficulty difficulty;
	private Integer categoryId;
	private List<Integer> contentIds;
	private Long registeredTime;
	private Integer registrant;
	private Long modifiedTime;
	private Integer modifier;

	public static Lecture create(LectureCdo createSdo) {
		return Lecture.builder()
			.name(createSdo.getName())
			.description(createSdo.getDescription())
			.learningType(createSdo.getLearningType())
			.lectureState(LectureState.Active)
			.thumbnail(createSdo.getThumbnail())
			.difficulty(createSdo.getDifficulty())
			.categoryId(createSdo.getCategoryId())
			.contentIds(new ArrayList<>())
			.registeredTime(System.currentTimeMillis())
			.registrant(createSdo.getRegistrant())
			.modifiedTime(System.currentTimeMillis())
			.modifier(createSdo.getRegistrant())
			.build();
	}

	public void modify(LectureUdo updateSdo) {
		if (updateSdo.getName() != null) {
			this.name = updateSdo.getName();
		}
		if (updateSdo.getDescription() != null) {
			this.description = updateSdo.getDescription();
		}
		if (updateSdo.getLearningType() != null) {
			this.learningType = updateSdo.getLearningType();
		}
		if (updateSdo.getLectureState() != null) {
			this.lectureState = updateSdo.getLectureState();
		}
		if (updateSdo.getThumbnail() != null) {
			this.thumbnail = updateSdo.getThumbnail();
		}
		if (updateSdo.getDifficulty() != null) {
			this.difficulty = updateSdo.getDifficulty();
		}
		if (updateSdo.getCategoryId() != null) {
			this.categoryId = updateSdo.getCategoryId();
		}
		this.modifiedTime = System.currentTimeMillis();
		this.modifier = updateSdo.getModifier();
	}

	public void addContent(Integer contentId) {
		if (this.contentIds == null) {
			this.contentIds = new ArrayList<>();
		}
		if (!this.contentIds.contains(contentId)) {
			this.contentIds.add(contentId);
			this.modifiedTime = System.currentTimeMillis();
		}
	}

	public void removeContent(Integer contentId) {
		if (this.contentIds != null && this.contentIds.contains(contentId)) {
			this.contentIds.remove(contentId);
			this.modifiedTime = System.currentTimeMillis();
		}
	}

	public void activate() {
		this.lectureState = LectureState.Active;
		this.modifiedTime = System.currentTimeMillis();
	}

	public void deactivate() {
		this.lectureState = LectureState.Inactive;
		this.modifiedTime = System.currentTimeMillis();
	}

	public void hide() {
		this.lectureState = LectureState.Hide;
		this.modifiedTime = System.currentTimeMillis();
	}

	public LectureRdo toRdo() {
		return LectureRdo.builder()
			.id(this.id)
			.name(this.name)
			.description(this.description)
			.learningType(this.learningType)
			.difficulty(this.difficulty)
			.thumbnail(this.thumbnail)
			.lectureState(this.lectureState)
			.categoryId(this.categoryId)
			.contentIds(this.contentIds)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(this.modifiedTime)
			.modifier(this.modifier)
			.build();
	}
}