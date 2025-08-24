package com.playground.backend.domain.match.entity;

import com.playground.backend.domain.ground.entity.Ground;
import com.playground.backend.domain.team.entity.Team;
import com.playground.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 경기 엔티티
 */
@Entity
@Table(name = "`match`")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ground_id")
    private Ground ground;

    @Column(name = "match_date")
    private LocalDateTime matchDate;
}
