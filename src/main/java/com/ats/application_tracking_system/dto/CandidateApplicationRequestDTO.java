package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.ApplicationPlatform;
import com.ats.application_tracking_system.enums.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CandidateApplicationRequestDTO {
    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Job role is required")
    private String jobRole;

    @NotNull(message = "Application platform is required")
    private ApplicationPlatform platform;

    @NotNull(message = "Application status is required")
    private ApplicationStatus status;

    @NotNull(message = "Applied date is required")
    private LocalDate appliedDate;
}
