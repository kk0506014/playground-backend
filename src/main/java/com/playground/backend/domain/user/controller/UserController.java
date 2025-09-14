package com.playground.backend.domain.user.controller;

import com.playground.backend.domain.user.dto.request.LogInRequest;
import com.playground.backend.domain.user.dto.request.SignUpRequest;
import com.playground.backend.domain.user.service.UserService;
import com.playground.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 유저 컨트롤러
 */
@SecurityRequirement(name = "BearerAuth")
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
    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    public ResponseEntity<ApiResponse<String>> signUp(@Valid @RequestBody SignUpRequest request) {
        userService.signUp(request);

        return ResponseEntity.ok(ApiResponse.success("회원가입 성공"));
    }

    /**
     * 로그인 엔드포인트
     *
     * @param request 로그인 요청 DTO
     * @param response HTTP 응답 객체
     * @return 성공 시 성공 메시지, 실패 시 에러 메시지
     */
    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<ApiResponse<String>> logIn(@Valid @RequestBody LogInRequest request, HttpServletResponse response) {
        String accessToken = userService.logIn(request);

        ResponseCookie cookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
//                .secure(true)
                .path("/")
                .maxAge(60 * 60)
                .sameSite("Strict")
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok(ApiResponse.success("로그인 성공"));
    }


    /**
     * 로그아웃 엔드포인트
     *
     * @param response HTTP 응답 객체
     * @return 성공 시 성공 메시지, 실패 시 에러 메시지
     */
    @PostMapping("/logout")
    @Operation(summary = "로그아웃")
    public ResponseEntity<ApiResponse<String>> logOut(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("accessToken", "")
                .httpOnly(true)
//                .secure(true)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok(ApiResponse.success("로그아웃 성공"));
    }
}
