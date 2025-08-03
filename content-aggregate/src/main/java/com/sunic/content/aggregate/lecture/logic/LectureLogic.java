package com.sunic.content.aggregate.lecture.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sunic.content.aggregate.lecture.store.LectureStore;
import com.sunic.content.aggregate.proxy.UserProxy;
import com.sunic.content.spec.common.exception.AdminPermissionException;
import com.sunic.content.spec.lecture.entity.Lecture;
import com.sunic.content.spec.lecture.facade.sdo.LectureCdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureQdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureRdo;
import com.sunic.content.spec.lecture.facade.sdo.LectureUdo;

import lombok.RequiredArgsConstructor;

/**
 * Business logic implementation for Lecture operations.
 * This follows the modularization-strategy.md pattern for Logic classes implementing CQRS.
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectureLogic {

	private final LectureStore lectureStore;
	private final UserProxy userProxy;

	@Transactional
	public Integer createLecture(LectureCdo createSdo) {
		if (!userProxy.checkUserIsAdmin(createSdo.getRegistrant())) {
			throw new AdminPermissionException(createSdo.getRegistrant());
		}

		Lecture lecture = Lecture.create(createSdo);
		Lecture saved = lectureStore.save(lecture);
		return saved.getId();
	}

	public LectureRdo retrieveLecture(Integer id) {
		Lecture lecture = lectureStore.findById(id);
		return convertToLectureRdo(lecture);
	}

	public List<LectureRdo> retrieveAllLectures() {
		return lectureStore.findAll().stream().map(this::convertToLectureRdo).collect(Collectors.toList());
	}

	public List<LectureRdo> searchLectures(LectureQdo searchSdo) {
		Sort sort = Sort.by(
			"DESC".equalsIgnoreCase(searchSdo.getSortDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC,
			searchSdo.getSortBy());

		Pageable pageable = PageRequest.of(searchSdo.getPage(), searchSdo.getSize(), sort);

		Page<Lecture> lecturePage = lectureStore.findByKeywordAndCategory(searchSdo.getKeyword(),
			searchSdo.getCategoryId(), pageable);

		return lecturePage.getContent().stream().map(this::convertToLectureRdo).collect(Collectors.toList());
	}

	@Transactional
	public void modifyLecture(Integer id, LectureUdo updateSdo) {
		if (!userProxy.checkUserIsAdmin(updateSdo.getModifier())) {
			throw new AdminPermissionException(updateSdo.getModifier());
		}

		Lecture existingLecture = lectureStore.findById(id);
		existingLecture.modify(updateSdo);
		lectureStore.save(existingLecture);
	}

	@Transactional
	public void deleteLecture(Integer id, Integer userId) {
		if (!userProxy.checkUserIsAdmin(userId)) {
			throw new AdminPermissionException(userId);
		}

		Lecture lecture = lectureStore.findById(id);
		lecture.deactivate();
		lectureStore.save(lecture);
	}

	private LectureRdo convertToLectureRdo(Lecture lecture) {
		return lecture.toRdo();
	}
}