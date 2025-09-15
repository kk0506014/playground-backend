package com.playground.backend.domain.sport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 스포츠 엔티티
 */
@Entity
@Table(name = "sport")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String name;

    @Column(name = "min_team_member", nullable = false)
    private Integer minTeamMember;
}
