package com.sunic.content.rest.rest.lecture.dto;

import com.sunic.content.spec.lecture.entity.Difficulty;
import com.sunic.content.spec.lecture.entity.LearningType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request DTO for creating lecture.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureCreateRequest {
    
    @NotNull(message = "Lecture name is required")
    private String name;
    
    private String description;
    
    @NotNull(message = "Learning type is required")
    private LearningType learningType;
    
    @NotNull(message = "Difficulty is required")
    private Difficulty difficulty;
    
    private String thumbnail;
    
    @NotNull(message = "Category ID is required")
    private Integer categoryId;
    
    @NotNull(message = "Registrant is required")
    private Integer registrant;
}