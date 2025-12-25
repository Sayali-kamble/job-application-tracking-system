package com.ats.application_tracking_system.dto;

public class AuthResponseDTO {
    private String message;
    private String token; // JWT

    public AuthResponseDTO(String message, String token) {
        this.message = message;
        this.token = token;
    }
}
