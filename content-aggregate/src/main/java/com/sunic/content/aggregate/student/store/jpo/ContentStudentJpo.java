package com.sunic.content.aggregate.student.store.jpo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * JPA entity for ContentStudent persistence.
 */
@Entity
@Table(name = "content_student")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ContentStudentJpo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private Integer studentId;

	@Column(nullable = false)
	private Integer contentId;

	private Integer progress;

	@Column(nullable = false)
	private Long registeredTime;

	@Column(nullable = false)
	private Long modifiedTime;
}