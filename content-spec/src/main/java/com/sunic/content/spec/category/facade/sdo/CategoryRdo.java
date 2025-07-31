package com.sunic.content.spec.category.facade.sdo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Response Data Object for category.
 * RDO pattern follows modularization-strategy.md guidelines for output DTOs.
 */
@Getter
@Builder
@ToString
public class CategoryRdo {
    private final Integer id;
    private final String name;
    private final String description;
    private final List<Integer> lectureIds;
    private final Long registeredTime;
    private final Integer registrant;
    private final Long modifiedTime;
    private final Integer modifier;
}