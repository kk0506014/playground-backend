package com.playground.backend.domain.user.service;

import com.playground.backend.domain.user.dto.request.ChangePasswordRequest;
import com.playground.backend.domain.user.dto.request.LogInRequest;
import com.playground.backend.domain.user.dto.request.SignUpRequest;
import com.playground.backend.domain.user.dto.request.UpdateRequest;
import com.playground.backend.domain.user.dto.response.UserResponse;
import com.playground.backend.domain.user.entity.User;
import com.playground.backend.domain.user.exception.UserErrorCode;
import com.playground.backend.domain.user.exception.UserException;
import com.playground.backend.domain.user.repository.UserRepository;
import com.playground.backend.global.auth.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 유저 서비스
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    /**
     * 회원가입 메서드
     *
     * @param signUpRequest 회원가입 요청 DTO
     * @throws UserException EMAIL_EXISTS
     * @throws UserException NICKNAME_EXISTS
     * @throws UserException PHONE_EXISTS
     */
    @Transactional
    public void signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserException(UserErrorCode.EMAIL_EXISTS);
        }

        if (userRepository.existsByNickName(signUpRequest.getNickName())) {
            throw new UserException(UserErrorCode.NICKNAME_EXISTS);
        }

        if (userRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            throw new UserException(UserErrorCode.PHONE_EXISTS);
        }

        User user = User.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .nickName(signUpRequest.getNickName())
                .fullName(signUpRequest.getFullName())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .birthDate(signUpRequest.getBirthDate())
                .profileImage(signUpRequest.getProfileImage())
                .role(User.RoleType.ROLE_USER)
                .build();

        userRepository.save(user);
    }

    /**
     * 로그인 메서드
     *
     *  @param logInRequest 로그인 요청 DTO
     *  @return JWT Access Token 문자열
     *  @throws UserException USER_NOT_FOUND
     *  @throws UserException INVALID_PASSWORD
     */
    @Transactional(readOnly = true)
    public String logIn(LogInRequest logInRequest) {
        User user = userRepository.findByEmail(logInRequest.getEmail())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(logInRequest.getPassword(), user.getPassword())) {
            throw new UserException(UserErrorCode.INVALID_PASSWORD);
        }

        return jwtProvider.generateToken(user.getEmail(), List.of(user.getRole().name()));
    }

    /**
     * 내 정보 조회 메서드
     *
     * @param email 로그인된 사용자의 이메일
     * @return UserResponse DTO
     * @throws UserException USER_NOT_FOUND
     */
    @Transactional(readOnly = true)
    public UserResponse getMyProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserResponse.from(user);
    }

    /**
     * 내 정보 수정 메서드
     *
     * @param email 로그인된 사용자의 이메일
     * @param updateRequest 내 정보 수정 요청 DTO
     * @return UserResponse 수정된 회원 정보 DTO
     * @throws UserException USER_NOT_FOUND
     */
    @Transactional
    public UserResponse updateMyProfile(String email, UpdateRequest updateRequest) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        user.updateProfile(updateRequest.getNickName(), updateRequest.getProfileImage());

        return UserResponse.from(user);
    }

    /**
     * 내 정보 삭제(탈퇴) 메서드
     *
     * @param email 로그인된 사용자의 이메일
     * @throws UserException USER_NOT_FOUND
     */
    @Transactional
    public void deleteMyAccount(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        userRepository.delete(user);
    }

    /**
     * 비밀번호 변경 메서드
     *
     * @param email 로그인된 사용자의 이메일
     * @param changePasswordRequest 비밀번호 변경 요청 DTO
     * @throws UserException USER_NOT_FOUND
     * @throws UserException INVALID_PASSWORD
     * @throws UserException PASSWORD_REUSE
     */
    @Transactional
    public void changePassword(String email, ChangePasswordRequest changePasswordRequest) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
            throw new UserException(UserErrorCode.INVALID_PASSWORD);
        }

        if (changePasswordRequest.getNewPassword().equals(changePasswordRequest.getCurrentPassword())) {
            throw new UserException(UserErrorCode.PASSWORD_REUSE);
        }

        user.changePassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
    }
}
