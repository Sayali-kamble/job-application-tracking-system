package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.UserRole;

public class RegisterRequestDTO {
    private String name;
    private String email;
    private String password;
    private UserRole role;
}
