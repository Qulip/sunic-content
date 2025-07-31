package com.sunic.content.spec.lecture.facade;

import com.sunic.content.spec.lecture.facade.sdo.LectureCreateSdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureRdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureSearchSdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureUpdateSdo;

import java.util.List;

/**
 * Facade interface for Lecture operations following CQRS pattern.
 */
public interface LectureFacade {
    
    Integer createLecture(LectureCreateSdo createSdo);
    
    LectureRdo retrieveLecture(Integer id);
    
    List<LectureRdo> retrieveAllLectures();
    
    List<LectureRdo> searchLectures(LectureSearchSdo searchSdo);
    
    void modifyLecture(Integer id, LectureUpdateSdo updateSdo);
    
    void deleteLecture(Integer id);
}