package com.playground.backend.domain.sport.exception;

import org.springframework.http.HttpStatus;

/**
 * 스포츠 예외 처리
 */
public class SportException extends RuntimeException {

    private final SportErrorCode sportErrorCode;

    public SportException(SportErrorCode sportErrorCode) {
        super(sportErrorCode.message);
        this.sportErrorCode = sportErrorCode;
    }

    public HttpStatus getStatus() {
        return sportErrorCode.status;
    }

    public String getCode() {
        return sportErrorCode.code;
    }
}
