package com.sunic.content.aggregate.category.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunic.content.aggregate.category.store.jpo.CategoryJpo;

/**
 * Spring Data repository for Category persistence.
 * This follows the modularization-strategy.md pattern for repository classes.
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryJpo, Integer> {
}