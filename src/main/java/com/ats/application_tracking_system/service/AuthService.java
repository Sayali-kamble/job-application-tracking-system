package com.ats.application_tracking_system.service;

import com.ats.application_tracking_system.dto.LoginRequestDTO;
import com.ats.application_tracking_system.dto.LoginResponseDTO;
import com.ats.application_tracking_system.model.User;
import com.ats.application_tracking_system.repository.UserRepository;
import com.ats.application_tracking_system.security.JwtUtil;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authenticationManager,
                       UserRepository userRepository,
                       JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(), dto.getPassword()
                )
        );

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow();

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new LoginResponseDTO(token, user.getRole().name());
    }
}
