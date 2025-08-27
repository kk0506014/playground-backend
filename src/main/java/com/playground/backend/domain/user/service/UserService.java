package com.playground.backend.domain.user.service;

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
     * @param email        이메일
     * @param rawPassword  평문 비밀번호
     * @param name         이름
     * @param profileImage 프로필 이미지
     * @return 회원가입이 완료된 User 엔티티
     * @throws IllegalArgumentException 이메일이 이미 존재할 경우
     */
    @Transactional
    public User registerUser(String email, String rawPassword, String name, String profileImage) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException();
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(rawPassword))
                .name(name)
                .profileImage(profileImage)
                .build();

        return userRepository.save(user);
    }
}
