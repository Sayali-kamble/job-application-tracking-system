package com.ats.application_tracking_system.dto;

public class LoginResponseDTO {

    private String token;
    private String role;

    public LoginResponseDTO(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }


}
