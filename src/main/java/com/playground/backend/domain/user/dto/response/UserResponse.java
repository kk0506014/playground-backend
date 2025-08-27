package com.playground.backend.domain.user.dto.response;

import com.playground.backend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * API 응답 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String email;

    private String name;

    private String profileImage;

    /**
     * UserResponse DTO 변환
     *
     * @param user 변환할 User 엔티티
     * @return UserResponse DTO
     */
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getProfileImage()
        );
    }
}
