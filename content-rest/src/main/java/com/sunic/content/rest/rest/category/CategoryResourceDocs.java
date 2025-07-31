package com.sunic.content.rest.rest.category;

import com.sunic.content.rest.rest.category.dto.CategoryCreateRequest;
import com.sunic.content.rest.rest.category.dto.CategoryResponse;
import com.sunic.content.rest.rest.category.dto.CategoryUpdateRequest;
import com.sunic.content.rest.rest.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * API documentation interface for Category operations.
 */
@Tag(name = "Category", description = "Category API")
public interface CategoryResourceDocs {
    
    @Operation(summary = "Create category", description = "Create a new category")
    ResponseEntity<ApiResponse<Integer>> createCategory(CategoryCreateRequest request);
    
    @Operation(summary = "Get category", description = "Get category by ID")
    ResponseEntity<ApiResponse<CategoryResponse>> getCategory(Integer id);
    
    @Operation(summary = "Get all categories", description = "Get all categories")
    ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories();
    
    @Operation(summary = "Update category", description = "Update category information")
    ResponseEntity<ApiResponse<Void>> updateCategory(Integer id, CategoryUpdateRequest request);
    
    @Operation(summary = "Delete category", description = "Delete category by ID")
    ResponseEntity<ApiResponse<Void>> deleteCategory(Integer id);
}