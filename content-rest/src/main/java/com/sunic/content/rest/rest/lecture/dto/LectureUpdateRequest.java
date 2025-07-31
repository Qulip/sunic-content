package com.sunic.content.rest.rest.lecture.dto;

import com.sunic.content.spec.lecture.entity.Difficulty;
import com.sunic.content.spec.lecture.entity.LearningType;
import com.sunic.content.spec.lecture.entity.LectureState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request DTO for updating lecture.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureUpdateRequest {
    
    private String name;
    private String description;
    private LearningType learningType;
    private Difficulty difficulty;
    private String thumbnail;
    private LectureState lectureState;
    private Integer categoryId;
    private Integer modifier;
}