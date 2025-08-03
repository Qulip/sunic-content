package com.sunic.content.rest.rest.student;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunic.content.spec.common.ApiResponse;
import com.sunic.content.spec.student.facade.StudentFacade;
import com.sunic.content.spec.student.facade.sdo.ContentStudentUdo;
import com.sunic.content.spec.student.facade.sdo.LectureStudentCdo;
import com.sunic.content.spec.student.facade.sdo.LectureStudentQdo;
import com.sunic.content.spec.student.facade.sdo.LectureStudentRdo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST API controller for Student operations.
 * This follows the modularization-strategy.md pattern for Resource controllers.
 */
@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentResource {

	private final StudentFacade studentFacade;

	@PostMapping("/lecture-students")
	public ResponseEntity<ApiResponse<String>> createLectureStudent(
		@Valid @RequestBody LectureStudentCdo lectureStudentCdo) {

		String lectureStudentId = studentFacade.createLectureStudent(lectureStudentCdo);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("Lecture student created successfully", lectureStudentId));
	}

	@GetMapping("/lecture-students/{id}")
	public ResponseEntity<ApiResponse<LectureStudentRdo>> getLectureStudent(@PathVariable Integer id) {
		return ResponseEntity.ok(
			ApiResponse.success("Lecture student retrieved successfully", studentFacade.retrieveLectureStudent(id))
		);
	}

	@PostMapping("/lecture-students/search")
	public ResponseEntity<ApiResponse<List<LectureStudentRdo>>> searchLectureStudents(
		@RequestBody LectureStudentQdo lectureStudentQdo) {
		return ResponseEntity.ok(
			ApiResponse.success("Lecture students retrieved successfully",
				studentFacade.searchLectureStudents(lectureStudentQdo))
		);
	}

	@PutMapping("/content-students")
	public ResponseEntity<ApiResponse<Void>> updateContentStudent(
		@Valid @RequestBody ContentStudentUdo contentStudentUdo) {

		studentFacade.updateContentStudent(contentStudentUdo);

		return ResponseEntity.ok(
			ApiResponse.success("Content student updated successfully")
		);
	}
}