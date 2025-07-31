package com.sunic.content.aggregate.category.logic;

import com.sunic.content.aggregate.category.store.CategoryStore;
import com.sunic.content.spec.category.entity.Category;
import com.sunic.content.spec.category.exception.CategoryHasLecturesException;
import com.sunic.content.spec.category.exception.CategoryNotFoundException;
import com.sunic.content.spec.category.facade.CategoryFacade;
import com.sunic.content.spec.category.facade.sdo.CategoryCreateSdo;
import com.sunic.content.spec.category.facade.sdo.CategoryRdo;
import com.sunic.content.spec.category.facade.sdo.CategoryUpdateSdo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Business logic implementation for Category operations.
 * This follows the modularization-strategy.md pattern for Logic classes implementing CQRS.
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryLogic implements CategoryFacade {
    
    private final CategoryStore categoryStore;
    
    @Override
    @Transactional
    public Integer createCategory(CategoryCreateSdo createSdo) {
        Category category = Category.create(createSdo);
        Category saved = categoryStore.save(category);
        return saved.getId();
    }
    
    @Override
    public CategoryRdo retrieveCategory(Integer id) {
        Category category = categoryStore.findById(id);
        return convertToCategoryRdo(category);
    }
    
    @Override
    public List<CategoryRdo> retrieveAllCategories() {
        return categoryStore.findAll().stream()
                .map(this::convertToCategoryRdo)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public void modifyCategory(Integer id, CategoryUpdateSdo updateSdo) {
        Category existingCategory = categoryStore.findById(id);
        Category updatedCategory = existingCategory.modify(updateSdo);
        categoryStore.save(updatedCategory);
    }
    
    @Override
    @Transactional
    public void deleteCategory(Integer id) {
        Category category = categoryStore.findById(id);
        
        if (categoryStore.hasLectures(id)) {
            throw new CategoryHasLecturesException(id);
        }
        
        categoryStore.deleteById(id);
    }
    
    private CategoryRdo convertToCategoryRdo(Category category) {
        return CategoryRdo.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .lectureIds(category.getLectureIds())
                .registeredTime(category.getRegisteredTime())
                .registrant(category.getRegistrant())
                .modifiedTime(category.getModifiedTime())
                .modifier(category.getModifier())
                .build();
    }
}