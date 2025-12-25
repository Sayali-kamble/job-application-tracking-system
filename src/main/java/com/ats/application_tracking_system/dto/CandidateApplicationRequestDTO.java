package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.ApplicationPlatform;
import com.ats.application_tracking_system.enums.ApplicationStatus;

import java.time.LocalDate;

public class CandidateApplicationRequestDTO {
    private String companyName;
    private String jobRole;
    private ApplicationPlatform platform;
    private ApplicationStatus status;
    private LocalDate appliedDate;
}
