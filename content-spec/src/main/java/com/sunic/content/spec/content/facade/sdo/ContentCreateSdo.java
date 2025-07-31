package com.sunic.content.spec.content.facade.sdo;

import com.sunic.content.spec.content.entity.ContentType;
import lombok.Builder;
import lombok.Getter;

/**
 * Service Data Object for creating Content.
 */
@Getter
@Builder
public class ContentCreateSdo {
    private final String name;
    private final String description;
    private final String url;
    private final ContentType contentType;
    private final Integer lectureId;
    private final Integer registrant;
}