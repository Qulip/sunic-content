package com.sunic.content.rest.rest.content.dto;

import com.sunic.content.spec.content.entity.ContentState;
import com.sunic.content.spec.content.entity.ContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request DTO for updating content.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentUpdateRequest {
    
    private String name;
    private String description;
    private String url;
    private ContentType contentType;
    private ContentState contentState;
    private Integer lectureId;
    private Integer modifier;
}