package com.playground.backend.domain.user.controller;

import com.playground.backend.domain.user.dto.request.LogInRequest;
import com.playground.backend.domain.user.dto.request.SignUpRequest;
import com.playground.backend.domain.user.service.UserService;
import com.playground.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 유저 컨트롤러
 */
@Tag(name = "유저")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입 엔드포인트
     *
     * @param request 회원가입 요청 DTO
     * @return 성공 시 성공 메시지, 실패 시 에러 메시지
     */
    @PostMapping("/sign-up")
    @Operation(summary = "회원가입")
    public ResponseEntity<ApiResponse<String>> signUp(@Valid @RequestBody SignUpRequest request) {
        userService.signUp(request);
        return ResponseEntity.ok(ApiResponse.success("회원가입 성공"));
    }

    /**
     * 로그인 엔드포인트
     *
     * @param request 로그인 요청 DTO
     * @return 성공 시 성공 메시지, 실패 시 에러 메시지
     */
    @PostMapping("/log-in")
    @Operation(summary = "로그인")
    public ResponseEntity<ApiResponse<String>> logIn(@Valid @RequestBody LogInRequest request,
            HttpServletResponse response) {
        String accessToken = userService.logIn(request);
        response.addHeader("Set-Cookie",
                "accessToken=" + accessToken);
        return ResponseEntity.ok(ApiResponse.success("로그인 성공"));
    }
}
