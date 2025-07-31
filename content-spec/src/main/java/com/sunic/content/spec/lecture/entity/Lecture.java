package com.sunic.content.spec.lecture.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Pure domain entity for Lecture without JPA annotations.
 */
@Getter
@Builder
@ToString
public class Lecture {
    private final Integer id;
    private final String name;
    private final String description;
    private final LearningType learningType;
    private final LectureState lectureState;
    private final String thumbnail;
    private final Difficulty difficulty;
    private final Integer categoryId;
    private final List<Integer> contentIds;
    private final Long registeredTime;
    private final Integer registrant;
    private final Long modifiedTime;
    private final Integer modifier;
}