package com.sunic.content.spec.common;

import lombok.Builder;
import lombok.Getter;

/**
 * Standardized API response wrapper for REST endpoints.
 * This follows the modularization-strategy.md pattern for common response handling.
 */
@Getter
@Builder
public class ApiResponse<T> {
	private final boolean success;
	private final String message;
	private final T data;

	public static <T> ApiResponse<T> success(String message, T data) {
		return ApiResponse.<T>builder()
			.success(true)
			.message(message)
			.data(data)
			.build();
	}

	public static <T> ApiResponse<T> success(String message) {
		return ApiResponse.<T>builder()
			.success(true)
			.message(message)
			.build();
	}

	public static <T> ApiResponse<T> error(String message) {
		return ApiResponse.<T>builder()
			.success(false)
			.message(message)
			.build();
	}
}