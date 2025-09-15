package com.playground.backend.domain.team.entity;

import com.playground.backend.domain.sport.entity.Sport;
import com.playground.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 팀 엔티티
 */
@Entity
@Table(name = "team")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String name;

    @Column(name = "logo_image")
    private String logoImage;

    @ManyToOne(fetch = FetchType.LAZY) // 한 팀은 하나의 스포츠만 가짐
    @JoinColumn(name = "sport", nullable = false)
    private Sport sport;
}
