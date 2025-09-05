package com.playground.backend.domain.user.exception;

import org.springframework.http.HttpStatus;

/**
 * 유저 에러 코드
 */
public enum UserErrorCode {

    EMAIL_EXISTS(HttpStatus.CONFLICT, "EMAIL_EXISTS", "이미 사용 중인 이메일입니다."),
    USERNAME_EXISTS(HttpStatus.CONFLICT, "USERNAME_EXISTS", "이미 사용 중인 이름입니다."),
    PHONE_EXISTS(HttpStatus.CONFLICT, "PHONE_EXISTS", "이미 사용 중인 번호입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "존재하지 않는 사용자입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "INVALID_PASSWORD", "비밀번호가 일치하지 않습니다.");

    public final HttpStatus status;
    public final String code;
    public final String message;

    UserErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
