package com.ats.application_tracking_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class JobSummaryDTO {
    private Long jobId;
    private String jobTitle;
    private Long totalCandidates;

    public JobSummaryDTO(Long jobId, String jobTitle, Long totalCandidates) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.totalCandidates = totalCandidates;
    }
}
