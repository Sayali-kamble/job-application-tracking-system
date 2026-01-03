package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.ApplicationPlatform;
import com.ats.application_tracking_system.enums.ApplicationStatus;

import java.time.LocalDate;

public class ApplicationSearchRequest {

    private String companyName;
    private String jobRole;
    private ApplicationPlatform platform;
    private ApplicationStatus status;

    private LocalDate startDate;
    private LocalDate endDate;

    public String getCompanyName() {
        return companyName;
    }

    public ApplicationPlatform getPlatform() {
        return platform;
    }

    public String getJobRole() {
        return jobRole;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
