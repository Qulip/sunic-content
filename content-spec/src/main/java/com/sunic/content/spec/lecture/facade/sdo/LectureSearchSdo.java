package com.sunic.content.spec.lecture.facade.sdo;

import lombok.Builder;
import lombok.Getter;

/**
 * Service Data Object for searching Lectures.
 */
@Getter
@Builder
public class LectureSearchSdo {
    private final int page;
    private final int size;
    private final String sortBy;
    private final String sortDirection;
    private final String keyword;
    private final Integer categoryId;
}