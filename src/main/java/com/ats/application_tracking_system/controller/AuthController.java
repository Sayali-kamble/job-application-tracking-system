package com.ats.application_tracking_system.controller;

import com.ats.application_tracking_system.dto.LoginRequestDTO;
import com.ats.application_tracking_system.dto.LoginResponseDTO;
import com.ats.application_tracking_system.dto.RegisterRequestDTO;
import com.ats.application_tracking_system.dto.RegisterResponseDTO;
import com.ats.application_tracking_system.service.AuthService;
import com.ats.application_tracking_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService,AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO request
    ) {
        RegisterResponseDTO response = userService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(
            @Valid @RequestBody LoginRequestDTO dto) {
        return authService.login(dto);
    }
}
