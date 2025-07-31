package com.sunic.content.rest.rest.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * REST request DTO for creating a category.
 * This follows the modularization-strategy.md pattern for REST-specific DTOs.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateRequest {
    
    @NotBlank(message = "Category name is required")
    private String name;
    
    private String description;
    
    @NotNull(message = "Registrant is required")
    private Integer registrant;
}