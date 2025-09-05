//package com.playground.backend.global.auth;
//
//import com.playground.backend.domain.user.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) {
//        return userRepository.findByEmail(email)
//                .map(CustomUserDetails::new)
//                .orElse(null);
//    }
//
//}
