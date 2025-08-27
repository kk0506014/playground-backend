package com.playground.backend.global.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

/**
 * API 응답 DTO
 */
@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private final ZonedDateTime timestamp;  // 응답 발생 시간
    private boolean success; // 요청 성공 여부
    private String message; // 응답 메시지
    private T data; // 실제 응답 데이터

    /**
     * 빌더 기반 생성자
     *
     * @param data    응답 데이터
     * @param message 응답 메시지
     * @param success 요청 성공 여부
     */
    @Builder(access = AccessLevel.PRIVATE)
    private ApiResponse(T data, String message, boolean success) {
        this.timestamp = ZonedDateTime.now();
        this.data = data;
        this.message = message;
        this.success = success;
    }

    /**
     * 성공 응답 생성 (데이터 + 메시지)
     *
     * @param data    응답 데이터
     * @param message 응답 메시지
     * @param <T>     데이터 타입
     *
     * @return 성공 ApiResponse 객체
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .data(data)
                .message(message)
                .success(true)
                .build();
    }

    /**
     * 성공 응답 생성 (데이터만)
     *
     * @param data    응답 데이터
     * @param <T>     데이터 타입
     *
     * @return 성공 ApiResponse 객체
     */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .data(data)
                .success(true)
                .build();
    }

    /**
     * 성공 응답 생성 (메시지만)
     *
     * @param message 응답 메시지
     * @param <T>     데이터 타입
     *
     * @return 성공 ApiResponse 객체
     */
    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .message(message)
                .success(true)
                .build();
    }

    /**
     * 성공 응답 생성 (반환값 없음)
     *
     * @param <T> 데이터 타입
     *
     * @return 성공 ApiResponse 객체
     */
    public static <T> ApiResponse<T> success() {
        return ApiResponse.<T>builder()
                .success(true)
                .build();
    }

    /**
     * 실패 응답 생성 (메시지 + 데이터)
     *
     * @param message 실패 메시지
     * @param data    응답 데이터 (optional)
     * @param <T>     데이터 타입
     *
     * @return 실패 ApiResponse 객체
     */
    public static <T> ApiResponse<T> fail(String message, T data) {
        return ApiResponse.<T>builder()
                .message(message)
                .data(data)
                .success(false)
                .build();
    }

    /**
     * 실패 응답 생성 (메시지만)
     *
     * @param message 실패 메시지
     * @param <T>     데이터 타입
     *
     * @return 실패 ApiResponse 객체
     */
    public static <T> ApiResponse<T> fail(String message) {
        return ApiResponse.<T>builder()
                .message(message)
                .success(false)
                .build();
    }

    /**
     * 실패 응답 생성 (반환값 없음)
     *
     * @param <T> 데이터 타입
     *
     * @return 실패 ApiResponse 객체
     */
    public static <T> ApiResponse<T> fail() {
        return ApiResponse.<T>builder()
                .success(false)
                .build();
    }
}
