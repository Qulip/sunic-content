package com.sunic.content.spec.category.facade.sdo;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Service Data Object for updating a category.
 */
@Getter
@Builder
@ToString
public class CategoryUdo {
	private final String name;
	private final String description;

	@NotNull(message = "Modifier is required")
	private final Integer modifier;
}