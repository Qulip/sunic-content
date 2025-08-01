package com.sunic.content.spec.category.entity;

import java.util.ArrayList;
import java.util.List;

import com.sunic.content.spec.category.facade.sdo.CategoryCdo;
import com.sunic.content.spec.category.facade.sdo.CategoryUdo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Pure domain entity for Category without JPA annotations.
 * This follows the modularization-strategy.md guideline for pure domain models.
 */
@Getter
@Builder
@ToString
public class Category {
	private final Integer id;
	private final String name;
	private final String description;
	private final List<Integer> lectureIds;
	private final Long registeredTime;
	private final Integer registrant;
	private final Long modifiedTime;
	private final Integer modifier;

	public static Category create(CategoryCdo createSdo) {
		return Category.builder()
			.name(createSdo.getName())
			.description(createSdo.getDescription())
			.lectureIds(new ArrayList<>())
			.registeredTime(System.currentTimeMillis())
			.registrant(createSdo.getRegistrant())
			.modifiedTime(System.currentTimeMillis())
			.modifier(createSdo.getRegistrant())
			.build();
	}

	public Category modify(CategoryUdo updateSdo) {
		return Category.builder()
			.id(this.id)
			.name(updateSdo.getName() != null ? updateSdo.getName() : this.name)
			.description(updateSdo.getDescription() != null ? updateSdo.getDescription() : this.description)
			.lectureIds(this.lectureIds)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(System.currentTimeMillis())
			.modifier(updateSdo.getModifier())
			.build();
	}

	public Category addLecture(Integer lectureId) {
		List<Integer> updatedLectureIds = new ArrayList<>(this.lectureIds);
		if (!updatedLectureIds.contains(lectureId)) {
			updatedLectureIds.add(lectureId);
		}
		return Category.builder()
			.id(this.id)
			.name(this.name)
			.description(this.description)
			.lectureIds(updatedLectureIds)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(System.currentTimeMillis())
			.modifier(this.modifier)
			.build();
	}

	public Category removeLecture(Integer lectureId) {
		List<Integer> updatedLectureIds = new ArrayList<>(this.lectureIds);
		updatedLectureIds.remove(lectureId);
		return Category.builder()
			.id(this.id)
			.name(this.name)
			.description(this.description)
			.lectureIds(updatedLectureIds)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(System.currentTimeMillis())
			.modifier(this.modifier)
			.build();
	}
}