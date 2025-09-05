package com.playground.backend.global.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtProvider {

    private final Key secretKey;
    private final long accessTokenValidity;

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-validity}") long accessTokenValidity
    ) {
        this.secretKey = generateSecretKey(secret);
        this.accessTokenValidity = accessTokenValidity;
    }

    /**
     * Key 객체 변환 메서드
     *
     * @param secret Base64 인코딩된 시크릿 문자열
     * @return 키 객체
     */
    private Key generateSecretKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    /**
     * JWT 토큰 생성 메서드
     *
     * @param email 사용자 이메일
     * @param roles 사용자 역할 목록
     * @return 생성된 JWT Access Token 문자열
     */
    public String generateToken(String email, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", roles);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(getExpireDate(now))
                .signWith(secretKey)
                .compact();
    }

//    public String generateToken(Authentication authentication) {
//        List<String> roles = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
//
//        return generateToken(authentication.getName(), roles);
//    }

    /**
     * 토큰 만료 계산 메서드
     *
     * @param now 현재 시간
     * @return Access Token 만료 시간
     */
    private Date getExpireDate(Date now) {
        return new Date(now.getTime() + accessTokenValidity);
    }

    /**
     * JWT 유효성 검증 메서드
     *
     * @param token 검증할 JWT 문자열
     * @return true, false
     */
    public boolean validateToken(String token) {
        try {
            Claims claims = parseToken(token);
            return !claims.getExpiration().before(new Date());
        }
        catch (ExpiredJwtException e) {
            log.warn("JWT expired: {}", e.getMessage());
        } catch (SecurityException e) {
            log.error("JWT signature invalid: {}", e.getMessage());
        } catch (JwtException e) {
            log.error("JWT invalid: {}", e.getMessage());
        } catch (Exception e) {
            log.error("JWT validation error: {}", e.getMessage());
        }
        return false;
    }

    /**
     * Authentication 객체 생성 메서드
     *
     * @param token 검증된 JWT 문자열
     * @return Spring Security 인증 객체
     */
    public Authentication getAuthentication(String token) {
        Claims claims = parseToken(token);
        String email = claims.getSubject();
        String role = (String) claims.get("roles");

        return new UsernamePasswordAuthenticationToken(
                email, "", List.of(new SimpleGrantedAuthority(role)));
    }

    /**
     * JWT 파싱 메서드
     *
     * @param token 검증할 JWT 문자열
     * @return Claims 객체
     * @throws JwtException JWT 파싱 중 오류 발생 시
     */
    private Claims parseToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
