package com.playground.backend.domain.user.dto.response;

import com.playground.backend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 다른 유저 프로필 조회 응답 DTO
 */
@AllArgsConstructor
@Getter
public class PublicUserProfileResponse {

    private String nickName;

    private String profileImage;

    /**
     * PublicUserProfileResponse DTO 변환 메서드
     *
     * @param user 변환할 User 엔티티
     * @return PublicUserProfileResponse DTO
     */
    public static PublicUserProfileResponse from(User user) {
        return new PublicUserProfileResponse(
                user.getNickName(),
                user.getProfileImage()
        );
    }
}
