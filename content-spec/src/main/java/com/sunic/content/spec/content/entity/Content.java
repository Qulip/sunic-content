package com.sunic.content.spec.content.entity;

import com.sunic.content.spec.content.facade.sdo.ContentCdo;
import com.sunic.content.spec.content.facade.sdo.ContentRdo;
import com.sunic.content.spec.content.facade.sdo.ContentUdo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Pure domain entity for Content without JPA annotations.
 */
@Getter
@Setter
@Builder
@ToString
public class Content {
	private Integer id;
	private String name;
	private String description;
	private String url;
	private ContentType contentType;
	private ContentState contentState;
	private Integer lectureId;
	private Long registeredTime;
	private Integer registrant;
	private Long modifiedTime;
	private Integer modifier;

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

	public void modify(ContentUdo updateSdo) {
		if (updateSdo.getName() != null) {
			this.name = updateSdo.getName();
		}
		if (updateSdo.getDescription() != null) {
			this.description = updateSdo.getDescription();
		}
		if (updateSdo.getUrl() != null) {
			this.url = updateSdo.getUrl();
		}
		if (updateSdo.getContentType() != null) {
			this.contentType = updateSdo.getContentType();
		}
		if (updateSdo.getContentState() != null) {
			this.contentState = updateSdo.getContentState();
		}
		if (updateSdo.getLectureId() != null) {
			this.lectureId = updateSdo.getLectureId();
		}
		this.modifiedTime = System.currentTimeMillis();
		this.modifier = updateSdo.getModifier();
	}

	public void activate() {
		this.contentState = ContentState.Active;
		this.modifiedTime = System.currentTimeMillis();
	}

	public void deactivate() {
		this.contentState = ContentState.Inactive;
		this.modifiedTime = System.currentTimeMillis();
	}

	public void remove() {
		this.contentState = ContentState.Removed;
		this.modifiedTime = System.currentTimeMillis();
	}

	public void delete() {
		this.contentState = ContentState.Deleted;
		this.modifiedTime = System.currentTimeMillis();
	}

	public ContentRdo toRdo() {
		return ContentRdo.builder()
			.id(this.id)
			.name(this.name)
			.description(this.description)
			.url(this.url)
			.contentType(this.contentType)
			.contentState(this.contentState)
			.lectureId(this.lectureId)
			.registeredTime(this.registeredTime)
			.registrant(this.registrant)
			.modifiedTime(this.modifiedTime)
			.modifier(this.modifier)
			.build();
	}
}