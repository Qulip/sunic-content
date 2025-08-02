package com.sunic.content.spec.category.facade;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sunic.content.spec.category.facade.sdo.CategoryCdo;
import com.sunic.content.spec.category.facade.sdo.CategoryRdo;
import com.sunic.content.spec.category.facade.sdo.CategoryUdo;
import com.sunic.content.spec.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * API documentation interface for Category operations.
 */
@Tag(name = "Category", description = "Category API")
public interface CategoryFacade {

	@Operation(summary = "Create category", description = "Create a new category")
	ResponseEntity<ApiResponse<Integer>> createCategory(CategoryCdo request);

	@Operation(summary = "Get category", description = "Get category by ID")
	ResponseEntity<ApiResponse<CategoryRdo>> getCategory(Integer id);

	@Operation(summary = "Get all categories", description = "Get all categories")
	ResponseEntity<ApiResponse<List<CategoryRdo>>> getAllCategories();

	@Operation(summary = "Update category", description = "Update category information")
	ResponseEntity<ApiResponse<Void>> updateCategory(Integer id, CategoryUdo request);

	@Operation(summary = "Delete category", description = "Delete category by ID")
	ResponseEntity<ApiResponse<Void>> deleteCategory(Integer id, Integer userId);
}