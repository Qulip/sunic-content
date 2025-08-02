package com.sunic.content.aggregate.student.store;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sunic.content.aggregate.student.store.jpo.ContentStudentJpo;
import com.sunic.content.aggregate.student.store.repository.ContentStudentRepository;
import com.sunic.content.spec.student.entity.ContentStudent;
import com.sunic.content.spec.student.exception.ContentStudentNotFoundException;

import lombok.RequiredArgsConstructor;

/**
 * Data access facade for ContentStudent operations.
 * This follows the modularization-strategy.md pattern for Store classes.
 */
@Component
@RequiredArgsConstructor
public class ContentStudentStore {

	private final ContentStudentRepository contentStudentRepository;

	public ContentStudent save(ContentStudent contentStudent) {
		ContentStudentJpo jpo = convertToJpo(contentStudent);
		ContentStudentJpo saved = contentStudentRepository.save(jpo);
		return convertToContentStudent(saved);
	}

	public ContentStudent findById(Integer id) {
		ContentStudentJpo jpo = contentStudentRepository.findById(id)
			.orElseThrow(() -> new ContentStudentNotFoundException(id));
		return convertToContentStudent(jpo);
	}

	public List<ContentStudent> findByStudentId(Integer studentId) {
		return contentStudentRepository.findByStudentId(studentId).stream()
			.map(this::convertToContentStudent)
			.collect(Collectors.toList());
	}

	public List<ContentStudent> findByContentId(Integer contentId) {
		return contentStudentRepository.findByContentId(contentId).stream()
			.map(this::convertToContentStudent)
			.collect(Collectors.toList());
	}

	public boolean existsById(Integer id) {
		return contentStudentRepository.existsById(id);
	}

	// Conversion methods between domain and persistence models
	private ContentStudent convertToContentStudent(ContentStudentJpo jpo) {
		return ContentStudent.builder()
			.id(jpo.getId())
			.studentId(jpo.getStudentId())
			.contentId(jpo.getContentId())
			.progress(jpo.getProgress())
			.registeredTime(jpo.getRegisteredTime())
			.modifiedTime(jpo.getModifiedTime())
			.build();
	}

	private ContentStudentJpo convertToJpo(ContentStudent contentStudent) {
		return ContentStudentJpo.builder()
			.id(contentStudent.getId())
			.studentId(contentStudent.getStudentId())
			.contentId(contentStudent.getContentId())
			.progress(contentStudent.getProgress())
			.registeredTime(contentStudent.getRegisteredTime())
			.modifiedTime(contentStudent.getModifiedTime())
			.build();
	}
}