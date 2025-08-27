package com.playground.backend.global.exception;

/**
 * 표준화된 예외 처리 응답 DTO
 * 모든 글로벌 예외 핸들러에서 클라이언트로 반환되는 JSON 형태를 정의
 */
public record ErrorResponse(String code, String message) {

    /**
     * ErrorResponse 객체를 생성하는 정적 팩토리 메서드
     *
     * @param code 에러 코드
     * @param message 에러 메시지
     * @return ErrorResponse 객체
     */
    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message);
    }
}
