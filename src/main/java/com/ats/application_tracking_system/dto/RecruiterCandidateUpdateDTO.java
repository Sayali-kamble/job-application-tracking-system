package com.ats.application_tracking_system.dto;

public class RecruiterCandidateUpdateDTO {
    private String fullName;
    private String phone;
    private CandidatePipelineStatus status;

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public CandidatePipelineStatus getStatus() {
        return status;
    }
}
