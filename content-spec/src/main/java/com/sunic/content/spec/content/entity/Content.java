package com.sunic.content.spec.content.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Pure domain entity for Content without JPA annotations.
 */
@Getter
@Builder
@ToString
public class Content {
    private final Integer id;
    private final String name;
    private final String description;
    private final String url;
    private final ContentType contentType;
    private final ContentState contentState;
    private final Integer lectureId;
    private final Long registeredTime;
    private final Integer registrant;
    private final Long modifiedTime;
    private final Integer modifier;
}