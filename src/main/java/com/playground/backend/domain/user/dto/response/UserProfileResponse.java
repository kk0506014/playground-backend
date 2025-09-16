package com.playground.backend.domain.user.dto.response;

import com.playground.backend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 내 정보 조회 응답 DTO
 */
@AllArgsConstructor
@Getter
public class UserProfileResponse {

    private Long id;

    private String email;

    private String nickName;

    private String fullName;

    private String phoneNumber;

    private LocalDate birthDate;

    private String profileImage;

    /**
     * UserProfileResponse DTO 변환 메서드
     *
     * @param user 변환할 User 엔티티
     * @return UserProfileResponse DTO
     */
    public static UserProfileResponse from(User user) {
        return new UserProfileResponse(
                user.getId(),
                user.getEmail(),
                user.getNickName(),
                user.getFullName(),
                user.getPhoneNumber(),
                user.getBirthDate(),
                user.getProfileImage()
        );
    }
}
