package com.playground.backend.domain.user.entity;

import com.playground.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유저 엔티티
 */
@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 100, unique = true, nullable = false)
    private String nickName;

    @Column(length = 100, nullable = false)
    private String fullName;

    @Column(length = 20, unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "profile_image")
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private RoleType role;

    public enum RoleType {
        ROLE_USER,
        ROLE_ADMIN
    }
}
