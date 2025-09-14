package com.playground.backend.domain.payment.entity;

import com.playground.backend.domain.reservation.entity.Reservation;
import com.playground.backend.domain.user.entity.User;
import com.playground.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 결제 엔티티
 */
@Entity
@Table(name = "payment")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false, length = 100)
    private String status; // ex: PENDING, COMPLETED, FAILED, CANCELLED

    @Column(nullable = false, length = 100)
    private String method; // ex: CARD, BANK_TRANSFER, KAKAO_PAY
}
