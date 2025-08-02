package com.sunic.content.aggregate.student.store;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sunic.content.aggregate.student.store.jpo.LectureStudentJpo;
import com.sunic.content.aggregate.student.store.repository.LectureStudentRepository;
import com.sunic.content.spec.student.entity.ContentStudent;
import com.sunic.content.spec.student.entity.LectureStudent;
import com.sunic.content.spec.student.exception.LectureStudentNotFoundException;

import lombok.RequiredArgsConstructor;

/**
 * Data access facade for LectureStudent operations.
 * This follows the modularization-strategy.md pattern for Store classes.
 */
@Component
@RequiredArgsConstructor
public class LectureStudentStore {

	private final LectureStudentRepository lectureStudentRepository;
	private final ContentStudentStore contentStudentStore;

	public LectureStudent save(LectureStudent lectureStudent) {
		LectureStudentJpo jpo = convertToJpo(lectureStudent);
		LectureStudentJpo saved = lectureStudentRepository.save(jpo);
		return convertToLectureStudent(saved);
	}

	public LectureStudent findById(Integer id) {
		LectureStudentJpo jpo = lectureStudentRepository.findById(id)
			.orElseThrow(() -> new LectureStudentNotFoundException(id));
		return convertToLectureStudent(jpo);
	}

	public List<LectureStudent> findByStudentId(Integer studentId) {
		return lectureStudentRepository.findByStudentId(studentId).stream()
			.map(this::convertToLectureStudent)
			.collect(Collectors.toList());
	}

	public List<LectureStudent> findByLectureId(Integer lectureId) {
		return lectureStudentRepository.findByLectureId(lectureId).stream()
			.map(this::convertToLectureStudent)
			.collect(Collectors.toList());
	}

	public boolean existsById(Integer id) {
		return lectureStudentRepository.existsById(id);
	}

	// Conversion methods between domain and persistence models
	private LectureStudent convertToLectureStudent(LectureStudentJpo jpo) {
		List<ContentStudent> contentStudents = null;
		if (jpo.getContentStudents() != null) {
			contentStudents = jpo.getContentStudents().stream()
				.map(contentStudentJpo -> ContentStudent.builder()
					.id(contentStudentJpo.getId())
					.studentId(contentStudentJpo.getStudentId())
					.contentId(contentStudentJpo.getContentId())
					.progress(contentStudentJpo.getProgress())
					.registeredTime(contentStudentJpo.getRegisteredTime())
					.modifiedTime(contentStudentJpo.getModifiedTime())
					.build())
				.collect(Collectors.toList());
		}

		return LectureStudent.builder()
			.id(jpo.getId())
			.studentId(jpo.getStudentId())
			.lectureId(jpo.getLectureId())
			.contentStudents(contentStudents)
			.registeredTime(jpo.getRegisteredTime())
			.modifiedTime(jpo.getModifiedTime())
			.build();
	}

	private LectureStudentJpo convertToJpo(LectureStudent lectureStudent) {
		return LectureStudentJpo.builder()
			.id(lectureStudent.getId())
			.studentId(lectureStudent.getStudentId())
			.lectureId(lectureStudent.getLectureId())
			.registeredTime(lectureStudent.getRegisteredTime())
			.modifiedTime(lectureStudent.getModifiedTime())
			.build();
	}
}