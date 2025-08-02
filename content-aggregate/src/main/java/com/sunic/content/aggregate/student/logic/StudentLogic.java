package com.sunic.content.aggregate.student.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sunic.content.aggregate.student.store.ContentStudentStore;
import com.sunic.content.aggregate.student.store.LectureStudentStore;
import com.sunic.content.spec.student.entity.ContentStudent;
import com.sunic.content.spec.student.entity.LectureStudent;
import com.sunic.content.spec.student.facade.StudentFacade;
import com.sunic.content.spec.student.facade.sdo.ContentStudentUdo;
import com.sunic.content.spec.student.facade.sdo.LectureStudentCdo;
import com.sunic.content.spec.student.facade.sdo.LectureStudentQdo;
import com.sunic.content.spec.student.facade.sdo.LectureStudentRdo;

import lombok.RequiredArgsConstructor;

/**
 * Business logic implementation for Student operations.
 * This follows the modularization-strategy.md pattern for Logic classes implementing CQRS.
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentLogic implements StudentFacade {

	private final LectureStudentStore lectureStudentStore;
	private final ContentStudentStore contentStudentStore;

	@Override
	@Transactional
	public String createLectureStudent(LectureStudentCdo lectureStudentCdo) {
		LectureStudent lectureStudent = LectureStudent.create(lectureStudentCdo);
		LectureStudent saved = lectureStudentStore.save(lectureStudent);
		return saved.getId().toString();
	}

	@Override
	public LectureStudentRdo retrieveLectureStudent(Integer id) {
		LectureStudent lectureStudent = lectureStudentStore.findById(id);
		return convertToLectureStudentRdo(lectureStudent);
	}

	@Override
	public List<LectureStudentRdo> searchLectureStudents(LectureStudentQdo lectureStudentQdo) {
		List<LectureStudent> lectureStudents;
		
		if (lectureStudentQdo.getStudentId() != null) {
			lectureStudents = lectureStudentStore.findByStudentId(lectureStudentQdo.getStudentId());
		} else if (lectureStudentQdo.getLectureId() != null) {
			lectureStudents = lectureStudentStore.findByLectureId(lectureStudentQdo.getLectureId());
		} else {
			lectureStudents = List.of();
		}
		
		return lectureStudents.stream()
			.map(this::convertToLectureStudentRdo)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void updateContentStudent(ContentStudentUdo contentStudentUdo) {
		ContentStudent existingContentStudent = contentStudentStore.findById(contentStudentUdo.getId());
		existingContentStudent.modify(contentStudentUdo);
		contentStudentStore.save(existingContentStudent);
	}

	private LectureStudentRdo convertToLectureStudentRdo(LectureStudent lectureStudent) {
		return lectureStudent.toRdo();
	}
}