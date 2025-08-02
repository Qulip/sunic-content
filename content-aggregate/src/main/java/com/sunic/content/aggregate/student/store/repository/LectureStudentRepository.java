package com.sunic.content.aggregate.student.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunic.content.aggregate.student.store.jpo.LectureStudentJpo;

/**
 * Repository interface for LectureStudent JPA operations.
 */
@Repository
public interface LectureStudentRepository extends JpaRepository<LectureStudentJpo, Integer> {
	
	List<LectureStudentJpo> findByStudentId(Integer studentId);
	
	List<LectureStudentJpo> findByLectureId(Integer lectureId);
}