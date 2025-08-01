package com.sunic.content.aggregate.content.store.jpo;

import com.sunic.content.spec.content.entity.ContentState;
import com.sunic.content.spec.content.entity.ContentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * JPA entity for Content persistence.
 */
@Entity
@Table(name = "content")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ContentJpo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	private String description;

	@Column(nullable = false)
	private String url;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ContentType contentType;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ContentState contentState;

	private Integer lectureId;

	@Column(nullable = false)
	private Long registeredTime;

	@Column(nullable = false)
	private Integer registrant;

	@Column(nullable = false)
	private Long modifiedTime;

	@Column(nullable = false)
	private Integer modifier;
}