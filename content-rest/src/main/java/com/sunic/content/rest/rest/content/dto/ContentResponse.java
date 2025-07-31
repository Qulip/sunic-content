package com.sunic.content.rest.rest.content.dto;

import com.sunic.content.spec.content.entity.ContentState;
import com.sunic.content.spec.content.entity.ContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Response DTO for content operations.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentResponse {
    
    private Integer id;
    private String name;
    private String description;
    private String url;
    private ContentType contentType;
    private ContentState contentState;
    private Integer lectureId;
    private Long registeredTime;
    private Integer registrant;
    private Long modifiedTime;
    private Integer modifier;
}