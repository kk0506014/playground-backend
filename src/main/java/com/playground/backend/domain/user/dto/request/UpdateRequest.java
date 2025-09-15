package com.playground.backend.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 내 정보 수정 요청 DTO
 */
@Getter
@NoArgsConstructor
public class UpdateRequest {

    private String nickName;

    private String profileImage;
}
