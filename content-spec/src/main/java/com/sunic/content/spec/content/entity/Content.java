package com.sunic.content.spec.content.entity;

import com.sunic.content.spec.content.facade.sdo.ContentCdo;
import com.sunic.content.spec.content.facade.sdo.ContentUdo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Pure domain entity for Content without JPA annotations.
 */
@Getter
@Builder
@ToString
public class Content {
	private final Integer id;
	private final String name;
	private final String description;
	private final String url;
	private final ContentType contentType;
	private final ContentState contentState;
	private final Integer lectureId;
	private final Long registeredTime;
	private final Integer registrant;
	private final Long modifiedTime;
	private final Integer modifier;

	public static Content create(ContentCdo createSdo) {
		return Content.builder()
			.name(createSdo.getName())
			.description(createSdo.getDescription())
			.url(createSdo.getUrl())
			.contentType(createSdo.getContentType())
			.contentState(ContentState.Active)
			.lectureId(createSdo.getLectureId())
			.registeredTime(System.currentTimeMillis())
			.registrant(createSdo.getRegistrant())
			.modifiedTime(System.currentTimeMillis())
			.modifier(createSdo.getRegistrant())
			.build();
	}

	public Content modify(ContentUdo updateSdo) {
		return Content.builder()
			.id(this.id)
			.name(updateSdo.getName() != null ? updateSdo.getName() : this.name)
			.description(updateSdo.getDescription() != null ? updateSdo.getDescription() : this.description)
			.url(updateSdo.getUrl() != null ? updateSdo.getUrl() : this.url)
			.contentType(updateSdo.getContentType() != null ? updateSdo.getContentType() : this.contentType)
			.contentState(updateSdo.getContentState() != null ? updateSdo.getContentState() : this.contentState)
			.lectureId(updateSdo.getLectureId() != null ? updateSdo.getLectureId() : this.lectureId)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(System.currentTimeMillis())
			.modifier(updateSdo.getModifier())
			.build();
	}

	public Content activate() {
		return Content.builder()
			.id(this.id)
			.name(this.name)
			.description(this.description)
			.url(this.url)
			.contentType(this.contentType)
			.contentState(ContentState.Active)
			.lectureId(this.lectureId)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(System.currentTimeMillis())
			.modifier(this.modifier)
			.build();
	}

	public Content deactivate() {
		return Content.builder()
			.id(this.id)
			.name(this.name)
			.description(this.description)
			.url(this.url)
			.contentType(this.contentType)
			.contentState(ContentState.Inactive)
			.lectureId(this.lectureId)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(System.currentTimeMillis())
			.modifier(this.modifier)
			.build();
	}

	public Content remove() {
		return Content.builder()
			.id(this.id)
			.name(this.name)
			.description(this.description)
			.url(this.url)
			.contentType(this.contentType)
			.contentState(ContentState.Removed)
			.lectureId(this.lectureId)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(System.currentTimeMillis())
			.modifier(this.modifier)
			.build();
	}

	public Content delete() {
		return Content.builder()
			.id(this.id)
			.name(this.name)
			.description(this.description)
			.url(this.url)
			.contentType(this.contentType)
			.contentState(ContentState.Deleted)
			.lectureId(this.lectureId)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(System.currentTimeMillis())
			.modifier(this.modifier)
			.build();
	}
}