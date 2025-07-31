package com.sunic.content.rest.rest.lecture.dto;

import com.sunic.content.spec.lecture.entity.Difficulty;
import com.sunic.content.spec.lecture.entity.LearningType;
import com.sunic.content.spec.lecture.entity.LectureState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response DTO for lecture operations.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureResponse {
    
    private Integer id;
    private String name;
    private String description;
    private LearningType learningType;
    private Difficulty difficulty;
    private String thumbnail;
    private LectureState lectureState;
    private Integer categoryId;
    private List<Integer> contentIds;
    private Long registeredTime;
    private Integer registrant;
    private Long modifiedTime;
    private Integer modifier;
}