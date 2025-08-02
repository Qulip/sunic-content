package com.sunic.content.spec.category.entity;

import java.util.ArrayList;
import java.util.List;

import com.sunic.content.spec.category.facade.sdo.CategoryCdo;
import com.sunic.content.spec.category.facade.sdo.CategoryRdo;
import com.sunic.content.spec.category.facade.sdo.CategoryUdo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Pure domain entity for Category without JPA annotations.
 * This follows the modularization-strategy.md guideline for pure domain models.
 */
@Getter
@Setter
@Builder
@ToString
public class Category {
	private Integer id;
	private String name;
	private String description;
	private List<Integer> lectureIds;
	private Long registeredTime;
	private Integer registrant;
	private Long modifiedTime;
	private Integer modifier;

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

	public void modify(CategoryUdo updateSdo) {
		if (updateSdo.getName() != null) {
			this.name = updateSdo.getName();
		}
		if (updateSdo.getDescription() != null) {
			this.description = updateSdo.getDescription();
		}
		this.modifiedTime = System.currentTimeMillis();
		this.modifier = updateSdo.getModifier();
	}

	public void addLecture(Integer lectureId) {
		if (this.lectureIds == null) {
			this.lectureIds = new ArrayList<>();
		}
		if (!this.lectureIds.contains(lectureId)) {
			this.lectureIds.add(lectureId);
			this.modifiedTime = System.currentTimeMillis();
		}
	}

	public void removeLecture(Integer lectureId) {
		if (this.lectureIds != null && this.lectureIds.contains(lectureId)) {
			this.lectureIds.remove(lectureId);
			this.modifiedTime = System.currentTimeMillis();
		}
	}

	public CategoryRdo toRdo() {
		return CategoryRdo.builder()
			.id(this.id)
			.name(this.name)
			.description(this.description)
			.lectureIds(this.lectureIds)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(this.modifiedTime)
			.modifier(this.modifier)
			.build();
	}
}