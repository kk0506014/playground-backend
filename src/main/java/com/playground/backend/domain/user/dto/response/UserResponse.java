package com.playground.backend.domain.user.dto.response;

import com.playground.backend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 유저 API 응답 DTO
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String email;

    private String nickName;

    private String fullName;

    private String phoneNumber;

    private LocalDate birthDate;

    private String profileImage;

    /**
     * UserResponse DTO 변환 메서드
     *
     * @param user 변환할 User 엔티티
     * @return UserResponse DTO
     */
    public static UserResponse from(User user) {
        return new UserResponse(
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
