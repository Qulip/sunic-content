package com.sunic.content.aggregate.student.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunic.content.aggregate.student.store.jpo.ContentStudentJpo;

/**
 * Repository interface for ContentStudent JPA operations.
 */
@Repository
public interface ContentStudentRepository extends JpaRepository<ContentStudentJpo, Integer> {

	List<ContentStudentJpo> findByStudentId(Integer studentId);

	List<ContentStudentJpo> findByContentId(Integer contentId);
}