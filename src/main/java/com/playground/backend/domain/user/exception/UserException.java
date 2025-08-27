package com.playground.backend.domain.user.exception;

import org.springframework.http.HttpStatus;

/**
 * 유저 예외 처리
 */
public class UserException extends RuntimeException {

    private final UserErrorCode userErrorCode;

    public UserException(UserErrorCode userErrorCode) {
        super(userErrorCode.message);
        this.userErrorCode = userErrorCode;
    }

    public HttpStatus getStatus() {
        return userErrorCode.status;
    }

    public String getCode() {
        return userErrorCode.code;
    }
}
