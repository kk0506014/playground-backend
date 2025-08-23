package com.playground.backend.domain.ground.entity;

import com.playground.backend.domain.sport.entity.Sport;
import com.playground.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 경기장 엔티티
 */
@Entity
@Table(name = "ground")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ground extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY) // 한 경기장은 하나의 스포츠만 가짐
    @JoinColumn(name = "sport", nullable = false)
    private Sport sport;

    @Column(nullable = false)
    private String location;

    @Column(name = "price_per_hour")
    private Integer pricePerHour;

    @Column(name = "ground_image")
    private String groundImage;
}
