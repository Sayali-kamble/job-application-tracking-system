package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.UserRole;
import lombok.Data;

@Data
public class RegisterResponseDTO {
    private String message;
    private UserRole role;

    public RegisterResponseDTO(String message, UserRole role) {
        this.message = message;
        this.role = role;
    }

    public String getMessage() { return message; }
    public UserRole getRole() { return role; }
}
