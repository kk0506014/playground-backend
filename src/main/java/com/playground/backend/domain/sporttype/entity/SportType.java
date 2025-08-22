package com.playground.backend.domain.sporttype.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 스포츠 타입 엔티티
 */
@Entity
@Table(name = "sport_type")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String name;
}
