package com.sunic.content.rest.rest.content;

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
import org.springframework.web.bind.annotation.RestController;

import com.sunic.content.spec.common.ApiResponse;
import com.sunic.content.spec.content.facade.ContentFacade;
import com.sunic.content.spec.content.facade.sdo.ContentCdo;
import com.sunic.content.spec.content.facade.sdo.ContentRdo;
import com.sunic.content.spec.content.facade.sdo.ContentUdo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST API controller for Content operations.
 * This follows the modularization-strategy.md pattern for Resource controllers.
 */
@RestController
@RequestMapping("/api/v1/contents")
@RequiredArgsConstructor
public class ContentResource {

	private final ContentFacade contentFacade;

	@PostMapping
	public ResponseEntity<ApiResponse<String>> registerContent(
		@Valid @RequestBody ContentCdo contentCdo) {

		String contentId = contentFacade.registerContent(contentCdo);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("Content registered successfully", contentId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ContentRdo>> getContent(@PathVariable Integer id) {
		return ResponseEntity.ok(
			ApiResponse.success("Content retrieved successfully", contentFacade.retrieveContent(id))
		);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<ContentRdo>>> getAllContents() {
		return ResponseEntity.ok(
			ApiResponse.success("Contents retrieved successfully", contentFacade.retrieveAllContents())
		);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> updateContent(
		@PathVariable Integer id,
		@Valid @RequestBody ContentUdo contentUdo) {

		contentFacade.modifyContent(id, contentUdo);

		return ResponseEntity.ok(
			ApiResponse.success("Content updated successfully")
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteContent(@PathVariable Integer id) {
		contentFacade.deleteContent(id);

		return ResponseEntity.ok(
			ApiResponse.success("Content deleted successfully")
		);
	}
}