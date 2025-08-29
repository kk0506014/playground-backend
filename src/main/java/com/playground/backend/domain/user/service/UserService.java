package com.playground.backend.domain.user.service;

import com.playground.backend.domain.user.dto.request.SignUpRequest;
import com.playground.backend.domain.user.entity.User;
import com.playground.backend.domain.user.exception.UserErrorCode;
import com.playground.backend.domain.user.exception.UserException;
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
     * @throws UserException EMAIL_EXISTS
     * @throws UserException USERNAME_EXISTS
     * @throws UserException PHONE_EXISTS
     */
    @Transactional
    public void signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserException(UserErrorCode.EMAIL_EXISTS);
        }

        if (userRepository.existsByUserName(request.getUserName())) {
            throw new UserException(UserErrorCode.USERNAME_EXISTS);
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new UserException(UserErrorCode.PHONE_EXISTS);
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userName(request.getUserName())
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .profileImage(request.getProfileImage())
                .build();

        userRepository.save(user);
    }
}
