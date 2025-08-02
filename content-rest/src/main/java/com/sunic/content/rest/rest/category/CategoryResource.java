package com.sunic.content.rest.rest.category;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunic.content.aggregate.category.logic.CategoryLogic;
import com.sunic.content.spec.category.facade.CategoryFacade;
import com.sunic.content.spec.category.facade.sdo.CategoryCdo;
import com.sunic.content.spec.category.facade.sdo.CategoryRdo;
import com.sunic.content.spec.category.facade.sdo.CategoryUdo;
import com.sunic.content.spec.common.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST API controller for Category operations.
 * This follows the modularization-strategy.md pattern for Resource controllers.
 */
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryResource implements CategoryFacade {

	private final CategoryLogic categoryLogic;

	@Override
	@PostMapping
	public ResponseEntity<ApiResponse<Integer>> createCategory(
		@Valid @RequestBody CategoryCdo categoryCdo) {

		Integer categoryId = categoryLogic.createCategory(categoryCdo);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("Category created successfully", categoryId));
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryRdo>> getCategory(@PathVariable Integer id) {
		return ResponseEntity.ok(
			ApiResponse.success("Category retrieved successfully", categoryLogic.retrieveCategory(id))
		);
	}

	@Override
	@GetMapping
	public ResponseEntity<ApiResponse<List<CategoryRdo>>> getAllCategories() {
		return ResponseEntity.ok(
			ApiResponse.success("Categories retrieved successfully", categoryLogic.retrieveAllCategories())
		);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> updateCategory(
		@PathVariable Integer id,
		@Valid @RequestBody CategoryUdo categoryUdo) {

		categoryLogic.modifyCategory(id, categoryUdo);

		return ResponseEntity.ok(
			ApiResponse.success("Category updated successfully")
		);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteCategory(
		@PathVariable Integer id,
		@RequestParam Integer userId) {
		categoryLogic.deleteCategory(id, userId);

		return ResponseEntity.ok(
			ApiResponse.success("Category deleted successfully")
		);
	}
}