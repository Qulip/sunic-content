package com.sunic.content.aggregate.content.logic;

import com.sunic.content.aggregate.content.store.ContentStore;
import com.sunic.content.spec.content.entity.Content;
import com.sunic.content.spec.content.entity.ContentState;
import com.sunic.content.spec.content.facade.ContentFacade;
import com.sunic.content.spec.content.facade.sdo.ContentCreateSdo;
import com.sunic.content.spec.content.facade.sdo.ContentRdo;
import com.sunic.content.spec.content.facade.sdo.ContentUpdateSdo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Business logic implementation for Content operations.
 * This follows the modularization-strategy.md pattern for Logic classes implementing CQRS.
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentLogic implements ContentFacade {
    
    private final ContentStore contentStore;
    
    @Override
    @Transactional
    public String registerContent(ContentCreateSdo createSdo) {
        Content content = Content.builder()
                .name(createSdo.getName())
                .description(createSdo.getDescription())
                .url(createSdo.getUrl())
                .contentType(createSdo.getContentType())
                .contentState(ContentState.Active)
                .lectureId(createSdo.getLectureId())
                .registeredTime(System.currentTimeMillis())
                .registrant(createSdo.getRegistrant())
                .modifiedTime(System.currentTimeMillis())
                .modifier(createSdo.getRegistrant())
                .build();
        
        Content saved = contentStore.save(content);
        return saved.getId().toString();
    }
    
    @Override
    public ContentRdo retrieveContent(Integer id) {
        Content content = contentStore.findById(id);
        return convertToContentRdo(content);
    }
    
    @Override
    public List<ContentRdo> retrieveAllContents() {
        return contentStore.findAll().stream()
                .map(this::convertToContentRdo)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public void modifyContent(Integer id, ContentUpdateSdo updateSdo) {
        Content existingContent = contentStore.findById(id);
        
        Content updatedContent = Content.builder()
                .id(existingContent.getId())
                .name(updateSdo.getName() != null ? updateSdo.getName() : existingContent.getName())
                .description(updateSdo.getDescription() != null ? updateSdo.getDescription() : existingContent.getDescription())
                .url(updateSdo.getUrl() != null ? updateSdo.getUrl() : existingContent.getUrl())
                .contentType(updateSdo.getContentType() != null ? updateSdo.getContentType() : existingContent.getContentType())
                .contentState(updateSdo.getContentState() != null ? updateSdo.getContentState() : existingContent.getContentState())
                .lectureId(updateSdo.getLectureId() != null ? updateSdo.getLectureId() : existingContent.getLectureId())
                .registeredTime(existingContent.getRegisteredTime())
                .registrant(existingContent.getRegistrant())
                .modifiedTime(System.currentTimeMillis())
                .modifier(updateSdo.getModifier())
                .build();
        
        contentStore.save(updatedContent);
    }
    
    @Override
    @Transactional
    public void deleteContent(Integer id) {
        Content content = contentStore.findById(id);
        
        Content updatedContent = Content.builder()
                .id(content.getId())
                .name(content.getName())
                .description(content.getDescription())
                .url(content.getUrl())
                .contentType(content.getContentType())
                .contentState(ContentState.Deleted)
                .lectureId(content.getLectureId())
                .registeredTime(content.getRegisteredTime())
                .registrant(content.getRegistrant())
                .modifiedTime(System.currentTimeMillis())  
                .modifier(content.getModifier())
                .build();
        
        contentStore.save(updatedContent);
    }

    private ContentRdo convertToContentRdo(Content content) {
        return ContentRdo.builder()
                .id(content.getId())
                .name(content.getName())
                .description(content.getDescription())
                .url(content.getUrl())
                .contentType(content.getContentType())
                .contentState(content.getContentState())
                .lectureId(content.getLectureId())
                .registeredTime(content.getRegisteredTime())
                .registrant(content.getRegistrant())
                .modifiedTime(content.getModifiedTime())
                .modifier(content.getModifier())
                .build();
    }
}