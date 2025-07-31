package com.sunic.content.aggregate.lecture.store.repository;

import com.sunic.content.aggregate.lecture.store.jpo.LectureJpo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Lecture JPA operations.
 */
@Repository
public interface LectureRepository extends JpaRepository<LectureJpo, Integer> {
    
    List<LectureJpo> findByCategoryId(Integer categoryId);
    
    @Query("SELECT l FROM LectureJpo l WHERE " +
           "(:keyword IS NULL OR l.name LIKE %:keyword% OR l.description LIKE %:keyword%) AND " +
           "(:categoryId IS NULL OR l.categoryId = :categoryId)")
    Page<LectureJpo> findByKeywordAndCategory(@Param("keyword") String keyword, 
                                              @Param("categoryId") Integer categoryId, 
                                              Pageable pageable);
}