package com.sunic.content.aggregate.lecture.logic;

import com.sunic.content.aggregate.lecture.store.LectureStore;
import com.sunic.content.spec.lecture.entity.Lecture;
import com.sunic.content.spec.lecture.entity.LectureState;
import com.sunic.content.spec.lecture.facade.LectureFacade;
import com.sunic.content.spec.lecture.facade.sdo.LectureCreateSdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureRdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureSearchSdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureUpdateSdo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Business logic implementation for Lecture operations.
 * This follows the modularization-strategy.md pattern for Logic classes implementing CQRS.
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectureLogic implements LectureFacade {
    
    private final LectureStore lectureStore;
    
    @Override
    @Transactional
    public Integer createLecture(LectureCreateSdo createSdo) {
        Lecture lecture = Lecture.create(createSdo);
        Lecture saved = lectureStore.save(lecture);
        return saved.getId();
    }
    
    @Override
    public LectureRdo retrieveLecture(Integer id) {
        Lecture lecture = lectureStore.findById(id);
        return convertToLectureRdo(lecture);
    }
    
    @Override
    public List<LectureRdo> retrieveAllLectures() {
        return lectureStore.findAll().stream()
                .map(this::convertToLectureRdo)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<LectureRdo> searchLectures(LectureSearchSdo searchSdo) {
        Sort sort = Sort.by("DESC".equalsIgnoreCase(searchSdo.getSortDirection()) ? 
                Sort.Direction.DESC : Sort.Direction.ASC, searchSdo.getSortBy());
        
        Pageable pageable = PageRequest.of(searchSdo.getPage(), searchSdo.getSize(), sort);
        
        Page<Lecture> lecturePage = lectureStore.findByKeywordAndCategory(
                searchSdo.getKeyword(), searchSdo.getCategoryId(), pageable);
        
        return lecturePage.getContent().stream()
                .map(this::convertToLectureRdo)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public void modifyLecture(Integer id, LectureUpdateSdo updateSdo) {
        Lecture existingLecture = lectureStore.findById(id);
        Lecture updatedLecture = existingLecture.modify(updateSdo);
        lectureStore.save(updatedLecture);
    }
    
    @Override
    @Transactional
    public void deleteLecture(Integer id) {
        Lecture lecture = lectureStore.findById(id);
        Lecture updatedLecture = lecture.deactivate();
        lectureStore.save(updatedLecture);
    }
    
    private LectureRdo convertToLectureRdo(Lecture lecture) {
        return LectureRdo.builder()
                .id(lecture.getId())
                .name(lecture.getName())
                .description(lecture.getDescription())
                .learningType(lecture.getLearningType())
                .difficulty(lecture.getDifficulty())
                .thumbnail(lecture.getThumbnail())
                .lectureState(lecture.getLectureState())
                .categoryId(lecture.getCategoryId())
                .contentIds(lecture.getContentIds())
                .registeredTime(lecture.getRegisteredTime())
                .registrant(lecture.getRegistrant())
                .modifiedTime(lecture.getModifiedTime())
                .modifier(lecture.getModifier())
                .build();
    }
}