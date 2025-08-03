package com.sunic.content.aggregate.category.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sunic.content.aggregate.category.store.CategoryStore;
import com.sunic.content.aggregate.proxy.UserProxy;
import com.sunic.content.spec.category.entity.Category;
import com.sunic.content.spec.category.exception.CategoryHasLecturesException;
import com.sunic.content.spec.category.facade.sdo.CategoryCdo;
import com.sunic.content.spec.category.facade.sdo.CategoryRdo;
import com.sunic.content.spec.category.facade.sdo.CategoryUdo;
import com.sunic.content.spec.common.exception.AdminPermissionException;

import lombok.RequiredArgsConstructor;

/**
 * Business logic implementation for Category operations.
 * This follows the modularization-strategy.md pattern for Logic classes implementing CQRS.
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryLogic {

	private final CategoryStore categoryStore;
	private final UserProxy userProxy;

	@Transactional
	public Integer createCategory(CategoryCdo createSdo) {
		if (!userProxy.checkUserIsAdmin(createSdo.getRegistrant())) {
			throw new AdminPermissionException(createSdo.getRegistrant());
		}

		Category category = Category.create(createSdo);
		Category saved = categoryStore.save(category);
		return saved.getId();
	}

	public CategoryRdo retrieveCategory(Integer id) {
		Category category = categoryStore.findById(id);
		return convertToCategoryRdo(category);
	}

	public List<CategoryRdo> retrieveAllCategories() {
		return categoryStore.findAll().stream()
			.map(this::convertToCategoryRdo)
			.collect(Collectors.toList());
	}

	@Transactional
	public void modifyCategory(Integer id, CategoryUdo updateSdo) {
		if (!userProxy.checkUserIsAdmin(updateSdo.getModifier())) {
			throw new AdminPermissionException(updateSdo.getModifier());
		}

		Category existingCategory = categoryStore.findById(id);
		existingCategory.modify(updateSdo);
		categoryStore.save(existingCategory);
	}

	@Transactional
	public void deleteCategory(Integer id, Integer userId) {
		if (!userProxy.checkUserIsAdmin(userId)) {
			throw new AdminPermissionException(userId);
		}

		Category category = categoryStore.findById(id);

		if (categoryStore.hasLectures(id)) {
			throw new CategoryHasLecturesException(id);
		}

		categoryStore.deleteById(id);
	}

	private CategoryRdo convertToCategoryRdo(Category category) {
		return category.toRdo();
	}
}