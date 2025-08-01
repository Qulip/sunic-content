package com.sunic.content.spec.lecture.facade.sdo;

import lombok.Builder;
import lombok.Getter;

/**
 * Service Data Object for searching Lectures.
 */
@Getter
@Builder
public class LectureQdo {

	@Builder.Default
	private int page = 0;

	@Builder.Default
	private int size = 20;

	@Builder.Default
	private String sortBy = "id";

	@Builder.Default
	private String sortDirection = "ASC";

	private String keyword;
	private Integer categoryId;
}