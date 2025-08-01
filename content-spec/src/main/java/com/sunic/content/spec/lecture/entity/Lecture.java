package com.sunic.content.spec.lecture.entity;

import java.util.ArrayList;
import java.util.List;

import com.sunic.content.spec.lecture.facade.sdo.LectureCdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureUdo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Pure domain entity for Lecture without JPA annotations.
 */
@Getter
@Builder
@ToString
public class Lecture {
	private final Integer id;
	private final String name;
	private final String description;
	private final LearningType learningType;
	private final LectureState lectureState;
	private final String thumbnail;
	private final Difficulty difficulty;
	private final Integer categoryId;
	private final List<Integer> contentIds;
	private final Long registeredTime;
	private final Integer registrant;
	private final Long modifiedTime;
	private final Integer modifier;

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

	public Lecture modify(LectureUdo updateSdo) {
		return Lecture.builder()
			.id(this.id)
			.name(updateSdo.getName() != null ? updateSdo.getName() : this.name)
			.description(updateSdo.getDescription() != null ? updateSdo.getDescription() : this.description)
			.learningType(updateSdo.getLearningType() != null ? updateSdo.getLearningType() : this.learningType)
			.lectureState(updateSdo.getLectureState() != null ? updateSdo.getLectureState() : this.lectureState)
			.thumbnail(updateSdo.getThumbnail() != null ? updateSdo.getThumbnail() : this.thumbnail)
			.difficulty(updateSdo.getDifficulty() != null ? updateSdo.getDifficulty() : this.difficulty)
			.categoryId(updateSdo.getCategoryId() != null ? updateSdo.getCategoryId() : this.categoryId)
			.contentIds(this.contentIds)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(System.currentTimeMillis())
			.modifier(updateSdo.getModifier())
			.build();
	}

	public Lecture addContent(Integer contentId) {
		List<Integer> updatedContentIds = new ArrayList<>(this.contentIds);
		if (!updatedContentIds.contains(contentId)) {
			updatedContentIds.add(contentId);
		}
		return Lecture.builder()
			.id(this.id)
			.name(this.name)
			.description(this.description)
			.learningType(this.learningType)
			.lectureState(this.lectureState)
			.thumbnail(this.thumbnail)
			.difficulty(this.difficulty)
			.categoryId(this.categoryId)
			.contentIds(updatedContentIds)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(System.currentTimeMillis())
			.modifier(this.modifier)
			.build();
	}

	public Lecture removeContent(Integer contentId) {
		List<Integer> updatedContentIds = new ArrayList<>(this.contentIds);
		updatedContentIds.remove(contentId);
		return Lecture.builder()
			.id(this.id)
			.name(this.name)
			.description(this.description)
			.learningType(this.learningType)
			.lectureState(this.lectureState)
			.thumbnail(this.thumbnail)
			.difficulty(this.difficulty)
			.categoryId(this.categoryId)
			.contentIds(updatedContentIds)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(System.currentTimeMillis())
			.modifier(this.modifier)
			.build();
	}

	public Lecture activate() {
		return Lecture.builder()
			.id(this.id)
			.name(this.name)
			.description(this.description)
			.learningType(this.learningType)
			.lectureState(LectureState.Active)
			.thumbnail(this.thumbnail)
			.difficulty(this.difficulty)
			.categoryId(this.categoryId)
			.contentIds(this.contentIds)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(System.currentTimeMillis())
			.modifier(this.modifier)
			.build();
	}

	public Lecture deactivate() {
		return Lecture.builder()
			.id(this.id)
			.name(this.name)
			.description(this.description)
			.learningType(this.learningType)
			.lectureState(LectureState.Inactive)
			.thumbnail(this.thumbnail)
			.difficulty(this.difficulty)
			.categoryId(this.categoryId)
			.contentIds(this.contentIds)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(System.currentTimeMillis())
			.modifier(this.modifier)
			.build();
	}

	public Lecture hide() {
		return Lecture.builder()
			.id(this.id)
			.name(this.name)
			.description(this.description)
			.learningType(this.learningType)
			.lectureState(LectureState.Hide)
			.thumbnail(this.thumbnail)
			.difficulty(this.difficulty)
			.categoryId(this.categoryId)
			.contentIds(this.contentIds)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(System.currentTimeMillis())
			.modifier(this.modifier)
			.build();
	}
}