package com.sunic.content.aggregate.content.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunic.content.aggregate.content.store.jpo.ContentJpo;

/**
 * Repository interface for Content JPA operations.
 */
@Repository
public interface ContentRepository extends JpaRepository<ContentJpo, Integer> {
}