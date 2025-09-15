package com.playground.backend.domain.user.controller;

import com.playground.backend.domain.user.dto.request.LogInRequest;
import com.playground.backend.domain.user.dto.request.SignUpRequest;
import com.playground.backend.domain.user.dto.request.UpdateRequest;
import com.playground.backend.domain.user.dto.response.UserResponse;
import com.playground.backend.domain.user.service.UserService;
import com.playground.backend.global.auth.CustomUserDetails;
import com.playground.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
     * @param logInRequest 로그인 요청 DTO
     * @param response HTTP 응답 객체
     * @return 성공 시 성공 메시지, 실패 시 에러 메시지
     */
    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<ApiResponse<String>> logIn(HttpServletResponse response, @Valid @RequestBody LogInRequest logInRequest) {
        String accessToken = userService.logIn(logInRequest);

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

    /**
     * 내 정보 조회 엔드포인트
     *
     * @param userDetails 인증된 사용자 정보가 담긴 CustomUserDetails
     * @return myProfile, 성공 시 성공 메시지, 실패 시 에러 메시지
     */
    @GetMapping("/me")
    @Operation(summary = "내 정보 조회")
    public ResponseEntity<ApiResponse<UserResponse>> getMyProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserResponse myProfile = userService.getMyProfile(userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success(myProfile, "내 정보 조회 성공"));
    }

    /**
     * 내 정보 수정 엔드포인트
     *
     * @param userDetails 인증된 사용자 정보가 담긴 CustomUserDetails
     * @param updateRequest 수정할 사용자 정보 DTO
     * @return updatedProfile, 성공 시 성공 메시지, 실패 시 에러 메시지
     */
    @PutMapping("/me")
    @Operation(summary = "내 정보 수정")
    public ResponseEntity<ApiResponse<UserResponse>> updateMyProfile(@AuthenticationPrincipal CustomUserDetails userDetails, @Valid @RequestBody UpdateRequest updateRequest) {
        UserResponse updatedProfile = userService.updateMyProfile(userDetails.getUsername(), updateRequest);
        return ResponseEntity.ok(ApiResponse.success(updatedProfile, "내 정보 수정 성공"));
    }
}
