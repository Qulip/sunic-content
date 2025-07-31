package com.sunic.content.spec.content.facade.sdo;

import com.sunic.content.spec.content.entity.ContentState;
import com.sunic.content.spec.content.entity.ContentType;
import lombok.Builder;
import lombok.Getter;

/**
 * Result Data Object for Content retrieval operations.
 */
@Getter
@Builder
public class ContentRdo {
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