package com.sunic.content.rest.rest.category;

import com.sunic.content.rest.rest.category.dto.CategoryCreateRequest;
import com.sunic.content.rest.rest.category.dto.CategoryResponse;
import com.sunic.content.rest.rest.category.dto.CategoryUpdateRequest;
import com.sunic.content.rest.rest.common.ApiResponse;
import com.sunic.content.spec.category.facade.CategoryFacade;
import com.sunic.content.spec.category.facade.sdo.CategoryCreateSdo;
import com.sunic.content.spec.category.facade.sdo.CategoryRdo;
import com.sunic.content.spec.category.facade.sdo.CategoryUpdateSdo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST API controller for Category operations.
 * This follows the modularization-strategy.md pattern for Resource controllers.
 */
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryResource implements CategoryResourceDocs {
    
    private final CategoryFacade categoryFacade;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> createCategory(
            @Valid @RequestBody CategoryCreateRequest request) {
        
        CategoryCreateSdo createSdo = CategoryCreateSdo.builder()
                .name(request.getName())
                .description(request.getDescription())
                .registrant(request.getRegistrant())
                .build();
        
        Integer categoryId = categoryFacade.createCategory(createSdo);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Category created successfully", categoryId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategory(@PathVariable Integer id) {
        CategoryRdo categoryRdo = categoryFacade.retrieveCategory(id);
        CategoryResponse response = convertToResponse(categoryRdo);
        
        return ResponseEntity.ok(
                ApiResponse.success("Category retrieved successfully", response)
        );
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories() {
        List<CategoryRdo> categoryRdos = categoryFacade.retrieveAllCategories();
        List<CategoryResponse> responses = categoryRdos.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(
                ApiResponse.success("Categories retrieved successfully", responses)
        );
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateCategory(
            @PathVariable Integer id,
            @Valid @RequestBody CategoryUpdateRequest request) {
        
        CategoryUpdateSdo updateSdo = CategoryUpdateSdo.builder()
                .name(request.getName())
                .description(request.getDescription())
                .modifier(request.getModifier())
                .build();
        
        categoryFacade.modifyCategory(id, updateSdo);
        
        return ResponseEntity.ok(
                ApiResponse.success("Category updated successfully")
        );
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Integer id) {
        categoryFacade.deleteCategory(id);
        
        return ResponseEntity.ok(
                ApiResponse.success("Category deleted successfully")
        );
    }
    
    private CategoryResponse convertToResponse(CategoryRdo rdo) {
        return CategoryResponse.builder()
                .id(rdo.getId())
                .name(rdo.getName())
                .description(rdo.getDescription())
                .lectureIds(rdo.getLectureIds())
                .registeredTime(rdo.getRegisteredTime())
                .registrant(rdo.getRegistrant())
                .modifiedTime(rdo.getModifiedTime())
                .modifier(rdo.getModifier())
                .build();
    }
}