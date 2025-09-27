package com.playground.backend.domain.sport.exception;

import org.springframework.http.HttpStatus;

/**
 * 스포츠 에러 코드
 */
public enum SportErrorCode {

    PASSWORD_REUSE1(HttpStatus.BAD_REQUEST, "PASSWORD_REUSE1", "새 비밀번호가 기존 비밀번호와 동일합니다.");

    public final HttpStatus status;
    public final String code;
    public final String message;

    SportErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
