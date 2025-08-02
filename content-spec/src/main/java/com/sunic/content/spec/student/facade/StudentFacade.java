package com.sunic.content.spec.student.facade;

import java.util.List;

import com.sunic.content.spec.student.facade.sdo.ContentStudentUdo;
import com.sunic.content.spec.student.facade.sdo.LectureStudentCdo;
import com.sunic.content.spec.student.facade.sdo.LectureStudentQdo;
import com.sunic.content.spec.student.facade.sdo.LectureStudentRdo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * API documentation interface for Student operations.
 */
@Tag(name = "Student", description = "Student API")
public interface StudentFacade {

	@Operation(summary = "Create Lecture Student", description = "Create a new lecture student enrollment")
	String createLectureStudent(LectureStudentCdo lectureStudentCdo);

	@Operation(summary = "Retrieve Lecture Student", description = "Retrieve lecture student")
	LectureStudentRdo retrieveLectureStudent(Integer id);

	@Operation(summary = "Search Lecture Students", description = "Search lecture students by criteria")
	List<LectureStudentRdo> searchLectureStudents(LectureStudentQdo lectureStudentQdo);

	@Operation(summary = "Update Content Student Progress", description = "Update content student progress")
	void updateContentStudent(ContentStudentUdo contentStudentUdo);
}