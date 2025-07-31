package com.sunic.content.rest.rest.content.dto;

import com.sunic.content.spec.content.entity.ContentType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request DTO for creating content.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentCreateRequest {
    
    @NotNull(message = "Content name is required")
    private String name;
    
    private String description;
    
    @NotNull(message = "Content URL is required")
    private String url;
    
    @NotNull(message = "Content type is required")
    private ContentType contentType;
    
    private Integer lectureId;
    
    @NotNull(message = "Registrant is required")
    private Integer registrant;
}