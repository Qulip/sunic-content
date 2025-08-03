package com.sunic.content.spec.lecture.facade;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sunic.content.spec.common.ApiResponse;
import com.sunic.content.spec.lecture.facade.sdo.LectureCdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureQdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureRdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureUdo;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * API documentation interface for Content operations.
 */
@Tag(name = "Lecture", description = "Lecture API")
public interface LectureFacade {
	ResponseEntity<ApiResponse<Integer>> createLecture(LectureCdo lectureCdo);

	ResponseEntity<ApiResponse<LectureRdo>> getLecture(Integer id);

	ResponseEntity<ApiResponse<List<LectureRdo>>> getAllLectures();

	ResponseEntity<ApiResponse<List<LectureRdo>>> searchLectures(LectureQdo lectureQdo);

	ResponseEntity<ApiResponse<Void>> updateLecture(Integer id, LectureUdo lectureUdo);

	ResponseEntity<ApiResponse<Void>> deleteLecture(Integer id, Integer userId);
}