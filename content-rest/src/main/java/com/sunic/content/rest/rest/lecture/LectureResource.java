package com.sunic.content.rest.rest.lecture;

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

import com.sunic.content.aggregate.lecture.logic.LectureLogic;
import com.sunic.content.spec.common.ApiResponse;
import com.sunic.content.spec.lecture.facade.LectureFacade;
import com.sunic.content.spec.lecture.facade.sdo.LectureCdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureQdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureRdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureUdo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST API controller for Lecture operations.
 * This follows the modularization-strategy.md pattern for Resource controllers.
 */
@RestController
@RequestMapping("/api/v1/lectures")
@RequiredArgsConstructor
public class LectureResource implements LectureFacade {

	private final LectureLogic lectureLogic;

	@Override
	@PostMapping("/")
	public ResponseEntity<ApiResponse<Integer>> createLecture(
		@Valid @RequestBody LectureCdo lectureCdo) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("Lecture created successfully", lectureLogic.createLecture(lectureCdo)));
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<LectureRdo>> getLecture(@PathVariable Integer id) {
		return ResponseEntity.ok(
			ApiResponse.success("Lecture retrieved successfully", lectureLogic.retrieveLecture(id))
		);
	}

	@Override
	@GetMapping("/")
	public ResponseEntity<ApiResponse<List<LectureRdo>>> getAllLectures() {
		return ResponseEntity.ok(
			ApiResponse.success("Lectures retrieved successfully", lectureLogic.retrieveAllLectures())
		);
	}

	@Override
	@PostMapping("/search")
	public ResponseEntity<ApiResponse<List<LectureRdo>>> searchLectures(
		@RequestBody LectureQdo lectureQdo) {
		return ResponseEntity.ok(
			ApiResponse.success("Lectures searched successfully", lectureLogic.searchLectures(lectureQdo))
		);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> updateLecture(
		@PathVariable Integer id,
		@Valid @RequestBody LectureUdo lectureUdo) {
		lectureLogic.modifyLecture(id, lectureUdo);

		return ResponseEntity.ok(
			ApiResponse.success("Lecture updated successfully")
		);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteLecture(
		@PathVariable Integer id,
		@RequestParam Integer userId) {
		lectureLogic.deleteLecture(id, userId);

		return ResponseEntity.ok(
			ApiResponse.success("Lecture deleted successfully")
		);
	}
}