package com.sunic.content.aggregate.lecture.store.jpo;

import com.sunic.content.spec.lecture.entity.Difficulty;
import com.sunic.content.spec.lecture.entity.LearningType;
import com.sunic.content.spec.lecture.entity.LectureState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * JPA Persistence Object for Lecture.
 */
@Entity
@Table(name = "lecture")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class LectureJpo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String name;
    
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LearningType learningType;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LectureState lectureState;
    
    private String thumbnail;
    
    @Column(nullable = false)
    private Integer categoryId;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;
    
    @Column(nullable = false)
    private Long registeredTime;
    
    @Column(nullable = false)
    private Integer registrant;
    
    @Column(nullable = false)
    private Long modifiedTime;
    
    @Column(nullable = false)
    private Integer modifier;
}