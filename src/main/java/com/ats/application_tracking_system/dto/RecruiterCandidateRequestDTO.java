package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.HiringStage;

public class RecruiterCandidateRequestDTO {
    private Long jobId;
    private String candidateName;
    private String email;
    private String phone;
    private HiringStage currentStage;
    private String resumeUrl;
    private String remarks;
}
