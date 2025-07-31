package com.sunic.content.aggregate.content.store;

import com.sunic.content.aggregate.content.store.jpo.ContentJpo;
import com.sunic.content.aggregate.content.store.repository.ContentRepository;
import com.sunic.content.spec.content.entity.Content;
import com.sunic.content.spec.content.exception.ContentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Data access facade for Content operations.
 * This follows the modularization-strategy.md pattern for Store classes.
 */
@Component
@RequiredArgsConstructor
public class ContentStore {
    
    private final ContentRepository contentRepository;
    
    public Content save(Content content) {
        ContentJpo jpo = convertToJpo(content);
        ContentJpo saved = contentRepository.save(jpo);
        return convertToContent(saved);
    }
    
    public Content findById(Integer id) {
        ContentJpo jpo = contentRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException(id));
        return convertToContent(jpo);
    }
    
    public List<Content> findAll() {
        return contentRepository.findAll().stream()
                .map(this::convertToContent)
                .collect(Collectors.toList());
    }
    
    public void deleteById(Integer id) {
        if (!contentRepository.existsById(id)) {
            throw new ContentNotFoundException(id);
        }
        contentRepository.deleteById(id);
    }
    
    public boolean existsById(Integer id) {
        return contentRepository.existsById(id);
    }
    
    // Conversion methods between domain and persistence models
    private Content convertToContent(ContentJpo jpo) {
        return Content.builder()
                .id(jpo.getId())
                .name(jpo.getName())
                .description(jpo.getDescription())
                .url(jpo.getUrl())
                .contentType(jpo.getContentType())
                .contentState(jpo.getContentState())
                .lectureId(jpo.getLectureId())
                .registeredTime(jpo.getRegisteredTime())
                .registrant(jpo.getRegistrant())
                .modifiedTime(jpo.getModifiedTime())
                .modifier(jpo.getModifier())
                .build();
    }
    
    private ContentJpo convertToJpo(Content content) {
        return ContentJpo.builder()
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