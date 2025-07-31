package com.sunic.content.spec.category.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

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
}