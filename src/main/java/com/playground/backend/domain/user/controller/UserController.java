package com.playground.backend.domain.user.controller;

import com.playground.backend.domain.user.dto.request.SignupRequest;
import com.playground.backend.domain.user.dto.response.UserResponse;
import com.playground.backend.domain.user.entity.User;
import com.playground.backend.domain.user.service.UserService;
import com.playground.backend.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 유저 컨트롤러
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입 엔드포인트
     *
     * @param request 회원가입 요청 DTO
     * @return 성공 시 User 엔티티, 실패 시 에러 메시지
     */
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResponse>> signup(@RequestBody SignupRequest request) {
        try {
            User user = userService.registerUser(
                    request.getEmail(),
                    request.getPassword(),
                    request.getName(),
                    request.getProfileImage()
            );
            return ResponseEntity.ok(ApiResponse.success(UserResponse.from(user), "회원가입 성공"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.fail("이미 존재하는 이메일입니다."));
        }
    }
}
