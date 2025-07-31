package com.sunic.content.spec.category.facade;

import com.sunic.content.spec.category.facade.sdo.CategoryCreateSdo;
import com.sunic.content.spec.category.facade.sdo.CategoryRdo;
import com.sunic.content.spec.category.facade.sdo.CategoryUpdateSdo;

import java.util.List;

/**
 * Category domain facade interface defining business operations contracts.
 * This follows the modularization-strategy.md pattern for service interfaces.
 */
public interface CategoryFacade {
    
    /**
     * Creates a new category
     */
    Integer createCategory(CategoryCreateSdo createSdo);
    
    /**
     * Retrieves a category by ID
     */
    CategoryRdo retrieveCategory(Integer id);
    
    /**
     * Retrieves all categories
     */
    List<CategoryRdo> retrieveAllCategories();
    
    /**
     * Modifies an existing category
     */
    void modifyCategory(Integer id, CategoryUpdateSdo updateSdo);
    
    /**
     * Deletes a category
     */
    void deleteCategory(Integer id);
}