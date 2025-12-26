package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.HiringStage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecruiterCandidateRequestDTO {
    @NotNull(message = "Job ID is required")
    private Long jobId;

    @NotBlank(message = "Candidate name is required")
    private String candidateName;

    private String email;
    private String phone;

    @NotNull(message = "Current hiring stage is required")
    private HiringStage currentStage;

    private String resumeUrl;
    private String remarks;
}
