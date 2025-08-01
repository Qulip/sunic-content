package com.sunic.content.aggregate.category.store;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sunic.content.aggregate.category.store.jpo.CategoryJpo;
import com.sunic.content.aggregate.category.store.repository.CategoryRepository;
import com.sunic.content.aggregate.lecture.store.LectureStore;
import com.sunic.content.spec.category.entity.Category;
import com.sunic.content.spec.category.exception.CategoryNotFoundException;

import lombok.RequiredArgsConstructor;

/**
 * Data access facade for Category operations.
 * This follows the modularization-strategy.md pattern for Store classes.
 */
@Component
@RequiredArgsConstructor
public class CategoryStore {

	private final CategoryRepository categoryRepository;
	private final LectureStore lectureStore;

	public Category save(Category category) {
		CategoryJpo jpo = convertToJpo(category);
		CategoryJpo saved = categoryRepository.save(jpo);
		return convertToCategory(saved);
	}

	public Category findById(Integer id) {
		CategoryJpo jpo = categoryRepository.findById(id)
			.orElseThrow(() -> new CategoryNotFoundException(id));
		return convertToCategory(jpo);
	}

	public List<Category> findAll() {
		return categoryRepository.findAll().stream()
			.map(this::convertToCategory)
			.collect(Collectors.toList());
	}

	public void deleteById(Integer id) {
		if (!categoryRepository.existsById(id)) {
			throw new CategoryNotFoundException(id);
		}
		categoryRepository.deleteById(id);
	}

	public boolean existsById(Integer id) {
		return categoryRepository.existsById(id);
	}

	public boolean hasLectures(Integer categoryId) {
		return !lectureStore.findByCategoryId(categoryId).isEmpty();
	}

	// Conversion methods between domain and persistence models
	private Category convertToCategory(CategoryJpo jpo) {
		return Category.builder()
			.id(jpo.getId())
			.name(jpo.getName())
			.description(jpo.getDescription())
			.lectureIds(List.of()) // Will be populated when needed by calling LectureStore
			.registeredTime(jpo.getRegisteredTime())
			.registrant(jpo.getRegistrant())
			.modifiedTime(jpo.getModifiedTime())
			.modifier(jpo.getModifier())
			.build();
	}

	private CategoryJpo convertToJpo(Category category) {
		return CategoryJpo.builder()
			.id(category.getId())
			.name(category.getName())
			.description(category.getDescription())
			.registeredTime(category.getRegisteredTime())
			.registrant(category.getRegistrant())
			.modifiedTime(category.getModifiedTime())
			.modifier(category.getModifier())
			.build();
	}
}