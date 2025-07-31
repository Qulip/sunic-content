package com.sunic.content.rest.rest.category.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * REST request DTO for updating a category.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateRequest {
    
    private String name;
    
    private String description;
    
    @NotNull(message = "Modifier is required")
    private Integer modifier;
}