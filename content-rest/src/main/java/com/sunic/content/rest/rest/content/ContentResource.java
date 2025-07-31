package com.sunic.content.rest.rest.content;

import com.sunic.content.rest.rest.common.ApiResponse;
import com.sunic.content.rest.rest.content.dto.ContentCreateRequest;
import com.sunic.content.rest.rest.content.dto.ContentResponse;
import com.sunic.content.rest.rest.content.dto.ContentUpdateRequest;
import com.sunic.content.spec.content.facade.ContentFacade;
import com.sunic.content.spec.content.facade.sdo.ContentCreateSdo;
import com.sunic.content.spec.content.facade.sdo.ContentRdo;
import com.sunic.content.spec.content.facade.sdo.ContentUpdateSdo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
            @Valid @RequestBody ContentCreateRequest request) {
        
        ContentCreateSdo createSdo = ContentCreateSdo.builder()
                .name(request.getName())
                .description(request.getDescription())
                .url(request.getUrl())
                .contentType(request.getContentType())
                .lectureId(request.getLectureId())
                .registrant(request.getRegistrant())
                .build();
        
        String contentId = contentFacade.registerContent(createSdo);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Content registered successfully", contentId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContentResponse>> getContent(@PathVariable Integer id) {
        ContentRdo contentRdo = contentFacade.retrieveContent(id);
        ContentResponse response = convertToResponse(contentRdo);
        
        return ResponseEntity.ok(
                ApiResponse.success("Content retrieved successfully", response)
        );
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<ContentResponse>>> getAllContents() {
        List<ContentRdo> contentRdos = contentFacade.retrieveAllContents();
        List<ContentResponse> responses = contentRdos.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(
                ApiResponse.success("Contents retrieved successfully", responses)
        );
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateContent(
            @PathVariable Integer id,
            @Valid @RequestBody ContentUpdateRequest request) {
        
        ContentUpdateSdo updateSdo = ContentUpdateSdo.builder()
                .name(request.getName())
                .description(request.getDescription())
                .url(request.getUrl())
                .contentType(request.getContentType())
                .contentState(request.getContentState())
                .lectureId(request.getLectureId())
                .modifier(request.getModifier())
                .build();
        
        contentFacade.modifyContent(id, updateSdo);
        
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
    
    private ContentResponse convertToResponse(ContentRdo rdo) {
        return ContentResponse.builder()
                .id(rdo.getId())
                .name(rdo.getName())
                .description(rdo.getDescription())
                .url(rdo.getUrl())
                .contentType(rdo.getContentType())
                .contentState(rdo.getContentState())
                .lectureId(rdo.getLectureId())
                .registeredTime(rdo.getRegisteredTime())
                .registrant(rdo.getRegistrant())
                .modifiedTime(rdo.getModifiedTime())
                .modifier(rdo.getModifier())
                .build();
    }
}