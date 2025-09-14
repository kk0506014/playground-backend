package com.playground.backend.global.auth;

import com.playground.backend.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * CustomUserDetails
 */
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;

    /**
     * 권한 정보 반환
     *
     * @return 권한 리스트
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    /**
     * 이메일 반환
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * 비밀번호 반환
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 계정 만료 여부 확인
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // 만료되지 않음
    }

    /**
     * 계정 잠금 여부 확인
     */
    @Override
    public boolean isAccountNonLocked() {
        return true; // 잠금되지 않음
    }

    /**
     * 자격 증명(비밀번호) 만료 여부 확인
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 자격 증명 만료되지 않음
    }

    /**
     * 계정 활성화 여부 확인
     */
    @Override
    public boolean isEnabled() {
        return true; // 계정 활성화됨
    }
}
