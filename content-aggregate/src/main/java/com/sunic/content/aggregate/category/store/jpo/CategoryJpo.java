package com.sunic.content.aggregate.category.store.jpo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * JPA Persistence Object for Category.
 * This follows the modularization-strategy.md pattern for JPO classes.
 */
@Entity
@Table(name = "category")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class CategoryJpo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String name;
    
    private String description;
    
    @Column(nullable = false)
    private Long registeredTime;
    
    @Column(nullable = false)
    private Integer registrant;
    
    @Column(nullable = false)
    private Long modifiedTime;
    
    @Column(nullable = false)
    private Integer modifier;
}