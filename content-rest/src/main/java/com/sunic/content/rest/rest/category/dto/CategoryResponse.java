package com.sunic.content.rest.rest.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * REST response DTO for category.
 */
@Getter
@Builder
@ToString
public class CategoryResponse {
    private final Integer id;
    private final String name;
    private final String description;
    private final List<Integer> lectureIds;
    private final Long registeredTime;
    private final Integer registrant;
    private final Long modifiedTime;
    private final Integer modifier;
}