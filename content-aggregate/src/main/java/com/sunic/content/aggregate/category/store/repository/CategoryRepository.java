package com.sunic.content.aggregate.category.store.repository;

import com.sunic.content.aggregate.category.store.jpo.CategoryJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for Category persistence.
 * This follows the modularization-strategy.md pattern for repository classes.
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryJpo, Integer> {
}