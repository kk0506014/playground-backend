package com.playground.backend.domain.user.entity;

import com.playground.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 유저 엔티티
 */
@Entity
@Table(name = "user")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
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

    @Column(nullable = false)
    private LocalDate birthDate;

    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private RoleType role;

    public enum RoleType {
        ROLE_USER,
        ROLE_ADMIN
    }

    /**
     * 정보 수정 메서드
     *
     * @param nickName      변경할 닉네임, null이면 기존 값 유지
     * @param profileImage  변경할 프로필 이미지, null이면 기존 값 유지
     */
    public void updateProfile(String nickName, String profileImage) {
        if (nickName != null && !nickName.isBlank()) {
            this.nickName = nickName;
        }
        if (profileImage != null && !profileImage.isBlank()) {
            this.profileImage = profileImage;
        }
    }

    /**
     * 비밀번호 변경 메서드
     *
     * @param newPassword 새 비밀번호
     */
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
