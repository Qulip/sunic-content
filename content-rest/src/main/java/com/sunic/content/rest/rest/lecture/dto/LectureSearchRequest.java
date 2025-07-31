package com.sunic.content.rest.rest.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request DTO for searching lectures.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureSearchRequest {
    
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