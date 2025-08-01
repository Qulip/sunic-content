package com.sunic.content.spec.content.facade.sdo;

import com.sunic.content.spec.content.entity.ContentType;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

/**
 * Service Data Object for creating Content.
 */
@Getter
@Builder
public class ContentCdo {

	@NotNull(message = "Content name is required")
	private String name;

	private String description;

	@NotNull(message = "Content URL is required")
	private String url;

	@NotNull(message = "Content type is required")
	private ContentType contentType;

	private Integer lectureId;

	@NotNull(message = "Registrant is required")
	private Integer registrant;
}