package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.HiringStage;
import lombok.Data;

@Data
public class RecruiterCandidateResponseDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private CandidatePipelineStatus status;
    private Long jobOpeningId;
    private String resumePath;
}
