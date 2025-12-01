package com.playground.backend.domain.sport.exception;

import org.springframework.http.HttpStatus;

/**
 * 스포츠 에러 코드
 */
public enum SportErrorCode {

    NAME_EXISTS(HttpStatus.BAD_REQUEST, "NAME_EXISTS", "이미 존재하는 스포츠 이름입니다."),
    INVALID_TEAM_MEMBER(HttpStatus.BAD_REQUEST, "INVALID_TEAM_MEMBER", "최소 팀 인원은 최대 팀 인원보다 클 수 없습니다.");

    public final HttpStatus status;
    public final String code;
    public final String message;

    SportErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
