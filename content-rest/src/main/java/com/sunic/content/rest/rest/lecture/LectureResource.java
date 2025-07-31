package com.sunic.content.rest.rest.lecture;

import com.sunic.content.rest.rest.common.ApiResponse;
import com.sunic.content.rest.rest.lecture.dto.LectureCreateRequest;
import com.sunic.content.rest.rest.lecture.dto.LectureResponse;
import com.sunic.content.rest.rest.lecture.dto.LectureSearchRequest;
import com.sunic.content.rest.rest.lecture.dto.LectureUpdateRequest;
import com.sunic.content.spec.lecture.facade.LectureFacade;
import com.sunic.content.spec.lecture.facade.sdo.LectureCreateSdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureRdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureSearchSdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureUpdateSdo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST API controller for Lecture operations.
 * This follows the modularization-strategy.md pattern for Resource controllers.
 */
@RestController
@RequestMapping("/api/v1/lectures")
@RequiredArgsConstructor
public class LectureResource {
    
    private final LectureFacade lectureFacade;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> createLecture(
            @Valid @RequestBody LectureCreateRequest request) {
        
        LectureCreateSdo createSdo = LectureCreateSdo.builder()
                .name(request.getName())
                .description(request.getDescription())
                .learningType(request.getLearningType())
                .difficulty(request.getDifficulty())
                .thumbnail(request.getThumbnail())
                .categoryId(request.getCategoryId())
                .registrant(request.getRegistrant())
                .build();
        
        Integer lectureId = lectureFacade.createLecture(createSdo);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Lecture created successfully", lectureId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LectureResponse>> getLecture(@PathVariable Integer id) {
        LectureRdo lectureRdo = lectureFacade.retrieveLecture(id);
        LectureResponse response = convertToResponse(lectureRdo);
        
        return ResponseEntity.ok(
                ApiResponse.success("Lecture retrieved successfully", response)
        );
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<LectureResponse>>> getAllLectures() {
        List<LectureRdo> lectureRdos = lectureFacade.retrieveAllLectures();
        List<LectureResponse> responses = lectureRdos.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(
                ApiResponse.success("Lectures retrieved successfully", responses)
        );
    }
    
    @PostMapping("/search")
    public ResponseEntity<ApiResponse<List<LectureResponse>>> searchLectures(
            @RequestBody LectureSearchRequest request) {
        
        LectureSearchSdo searchSdo = LectureSearchSdo.builder()
                .page(request.getPage())
                .size(request.getSize())
                .sortBy(request.getSortBy())
                .sortDirection(request.getSortDirection())
                .keyword(request.getKeyword())
                .categoryId(request.getCategoryId())
                .build();
        
        List<LectureRdo> lectureRdos = lectureFacade.searchLectures(searchSdo);
        List<LectureResponse> responses = lectureRdos.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(
                ApiResponse.success("Lectures searched successfully", responses)
        );
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateLecture(
            @PathVariable Integer id,
            @Valid @RequestBody LectureUpdateRequest request) {
        
        LectureUpdateSdo updateSdo = LectureUpdateSdo.builder()
                .name(request.getName())
                .description(request.getDescription())
                .learningType(request.getLearningType())
                .difficulty(request.getDifficulty())
                .thumbnail(request.getThumbnail())
                .lectureState(request.getLectureState())
                .categoryId(request.getCategoryId())
                .modifier(request.getModifier())
                .build();
        
        lectureFacade.modifyLecture(id, updateSdo);
        
        return ResponseEntity.ok(
                ApiResponse.success("Lecture updated successfully")
        );
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteLecture(@PathVariable Integer id) {
        lectureFacade.deleteLecture(id);
        
        return ResponseEntity.ok(
                ApiResponse.success("Lecture deleted successfully")
        );
    }
    
    private LectureResponse convertToResponse(LectureRdo rdo) {
        return LectureResponse.builder()
                .id(rdo.getId())
                .name(rdo.getName())
                .description(rdo.getDescription())
                .learningType(rdo.getLearningType())
                .difficulty(rdo.getDifficulty())
                .thumbnail(rdo.getThumbnail())
                .lectureState(rdo.getLectureState())
                .categoryId(rdo.getCategoryId())
                .contentIds(rdo.getContentIds())
                .registeredTime(rdo.getRegisteredTime())
                .registrant(rdo.getRegistrant())
                .modifiedTime(rdo.getModifiedTime())
                .modifier(rdo.getModifier())
                .build();
    }
}