package com.playground.backend.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 비밀번호 요청 DTO
 */
@Getter
@NoArgsConstructor
public class PasswordRequest {

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
