package com.sunic.content.aggregate.lecture.store;

import com.sunic.content.aggregate.lecture.store.jpo.LectureJpo;
import com.sunic.content.aggregate.lecture.store.repository.LectureRepository;
import com.sunic.content.spec.lecture.entity.Lecture;
import com.sunic.content.spec.lecture.exception.LectureNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Data access facade for Lecture operations.
 * This follows the modularization-strategy.md pattern for Store classes.
 */
@Component
@RequiredArgsConstructor
public class LectureStore {
    
    private final LectureRepository lectureRepository;
    
    public Lecture save(Lecture lecture) {
        LectureJpo jpo = convertToJpo(lecture);
        LectureJpo saved = lectureRepository.save(jpo);
        return convertToLecture(saved);
    }
    
    public Lecture findById(Integer id) {
        LectureJpo jpo = lectureRepository.findById(id)
                .orElseThrow(() -> new LectureNotFoundException(id));
        return convertToLecture(jpo);
    }
    
    public List<Lecture> findAll() {
        return lectureRepository.findAll().stream()
                .map(this::convertToLecture)
                .collect(Collectors.toList());
    }
    
    public List<Lecture> findByCategoryId(Integer categoryId) {
        return lectureRepository.findByCategoryId(categoryId).stream()
                .map(this::convertToLecture)
                .collect(Collectors.toList());
    }
    
    public Page<Lecture> findByKeywordAndCategory(String keyword, Integer categoryId, Pageable pageable) {
        Page<LectureJpo> jpoPage = lectureRepository.findByKeywordAndCategory(keyword, categoryId, pageable);
        return jpoPage.map(this::convertToLecture);
    }
    
    public void deleteById(Integer id) {
        if (!lectureRepository.existsById(id)) {
            throw new LectureNotFoundException(id);
        }
        lectureRepository.deleteById(id);
    }
    
    public boolean existsById(Integer id) {
        return lectureRepository.existsById(id);
    }
    
    // Conversion methods between domain and persistence models
    private Lecture convertToLecture(LectureJpo jpo) {
        return Lecture.builder()
                .id(jpo.getId())
                .name(jpo.getName())
                .description(jpo.getDescription())
                .learningType(jpo.getLearningType())
                .lectureState(jpo.getLectureState())
                .thumbnail(jpo.getThumbnail())
                .categoryId(jpo.getCategoryId())
                .difficulty(jpo.getDifficulty())
                .contentIds(List.of()) // Will be populated when needed
                .registeredTime(jpo.getRegisteredTime())
                .registrant(jpo.getRegistrant())
                .modifiedTime(jpo.getModifiedTime())
                .modifier(jpo.getModifier())
                .build();
    }
    
    private LectureJpo convertToJpo(Lecture lecture) {
        return LectureJpo.builder()
                .id(lecture.getId())
                .name(lecture.getName())
                .description(lecture.getDescription())
                .learningType(lecture.getLearningType())
                .lectureState(lecture.getLectureState())
                .thumbnail(lecture.getThumbnail())
                .categoryId(lecture.getCategoryId())
                .difficulty(lecture.getDifficulty())
                .registeredTime(lecture.getRegisteredTime())
                .registrant(lecture.getRegistrant())
                .modifiedTime(lecture.getModifiedTime())
                .modifier(lecture.getModifier())
                .build();
    }
}