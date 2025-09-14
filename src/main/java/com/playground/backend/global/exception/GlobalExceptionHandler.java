package com.playground.backend.global.exception;

import com.playground.backend.domain.user.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 글로벌 예외 처리 핸들러
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 처리되지 않은 일반 예외 처리 메서드
     *
     * @param ex 발생한 일반 예외
     * @return ResponseEntity<ErrorResponse> 표준화된 에러 응답
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        log.error("UnhandledException", ex);
        return ResponseEntity.status(500)
                .body(ErrorResponse.of(
                        "INTERNAL_SERVER_ERROR",
                        "서버 내부 오류가 발생했습니다."
                ));
    }

    /**
     * ValidationException 처리 메서드
     *
     * @param ex 발생한 ValidationException
     * @return ResponseEntity<ErrorResponse> 사용자 정의 에러 응답
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        log.warn("ValidationException: {}", ex.getMessage());
        return ResponseEntity.badRequest()
                .body(ErrorResponse.of(
                        "VALIDATION_ERROR",
                        ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()
                ));
    }

    /**
     * UserException 처리 메서드
     *
     * @param ex 발생한 UserException
     * @return ResponseEntity<ErrorResponse> 사용자 정의 에러 응답
     */
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserException ex) {
        log.warn("UserException: {}", ex.getMessage());
        return ResponseEntity.status(ex.getStatus())
                .body(ErrorResponse.of(
                        ex.getCode(),
                        ex.getMessage()
                ));
    }
}
