package com.ats.application_tracking_system.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecruiterDashboardDTO {

    private Long openPositions;
    private List<JobSummaryDTO> candidatesPerJob;
    private List<StageCountDTO> candidatesByStage;
}

