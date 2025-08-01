package com.sunic.content.spec.category.facade.sdo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Service Data Object for creating a category.
 * SDO pattern follows modularization-strategy.md guidelines for input DTOs.
 */
@Getter
@Builder
@ToString
public class CategoryCdo {
	private final String name;
	private final String description;
	private final Integer registrant;
}