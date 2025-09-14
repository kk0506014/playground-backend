package com.playground.backend.domain.user.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 회원가입 요청 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, message = "비밀번호는 8자리 이상이어야 합니다.")
//    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
//            message = "비밀번호는 8자리 이상, 대문자, 소문자, 숫자, 특수문자를 포함해야 합니다.")
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickName;

    @NotBlank(message = "이름을 입력해주세요.")
    private String fullName;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "전화번호 형식이 올바르지 않습니다. (ex: 010-1234-5678)")
    private String phoneNumber;

    @NotNull(message = "생년월일을 입력해주세요.")
    private LocalDate birthDate;

    private String profileImage;
}
