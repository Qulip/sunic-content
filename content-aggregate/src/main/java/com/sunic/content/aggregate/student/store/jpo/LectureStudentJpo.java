package com.sunic.content.aggregate.student.store.jpo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * JPA entity for LectureStudent persistence.
 */
@Entity
@Table(name = "lecture_student")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class LectureStudentJpo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private Integer studentId;

	@Column(nullable = false)
	private Integer lectureId;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "lecture_student_id")
	private List<ContentStudentJpo> contentStudents;

	@Column(nullable = false)
	private Long registeredTime;

	@Column(nullable = false)
	private Long modifiedTime;
}