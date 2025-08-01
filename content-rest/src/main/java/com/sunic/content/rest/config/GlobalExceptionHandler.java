package com.sunic.content.rest.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sunic.content.spec.category.exception.CategoryHasLecturesException;
import com.sunic.content.spec.category.exception.CategoryNotFoundException;
import com.sunic.content.spec.common.ApiResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Global exception handler for REST layer.
 * This follows the modularization-strategy.md pattern for centralized exception handling.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleCategoryNotFound(CategoryNotFoundException ex) {
		log.error("Category not found: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(ApiResponse.error(ex.getMessage()));
	}

	@ExceptionHandler(CategoryHasLecturesException.class)
	public ResponseEntity<ApiResponse<Void>> handleCategoryHasLectures(CategoryHasLecturesException ex) {
		log.error("Category has lectures: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ApiResponse.error(ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
		MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ApiResponse.<Map<String, String>>builder()
				.success(false)
				.message("Validation failed")
				.data(errors)
				.build());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
		log.error("Unexpected error: {}", ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(ApiResponse.error("An unexpected error occurred"));
	}
}