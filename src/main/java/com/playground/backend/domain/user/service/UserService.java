package com.playground.backend.domain.user.service;

import com.playground.backend.domain.user.dto.request.SignupRequest;
import com.playground.backend.domain.user.entity.User;
import com.playground.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 유저 서비스
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입 메서드
     *
     * @param request 회원가입 요청 DTO
     * @return 회원가입이 완료된 User 엔티티
     * @throws IllegalArgumentException 이메일이 이미 존재할 경우
     */
    @Transactional
    public User registerUser(SignupRequest request) {

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userName(request.getUserName())
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .profileImage(request.getProfileImage())
                .build();

        return userRepository.save(user);
    }
}
