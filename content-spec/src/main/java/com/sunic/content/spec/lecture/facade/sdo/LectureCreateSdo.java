package com.sunic.content.spec.lecture.facade.sdo;

import com.sunic.content.spec.lecture.entity.Difficulty;
import com.sunic.content.spec.lecture.entity.LearningType;
import lombok.Builder;
import lombok.Getter;

/**
 * Service Data Object for creating Lecture.
 */
@Getter
@Builder
public class LectureCreateSdo {
    private final String name;
    private final String description;
    private final LearningType learningType;
    private final Difficulty difficulty;
    private final String thumbnail;
    private final Integer categoryId;
    private final Integer registrant;
}