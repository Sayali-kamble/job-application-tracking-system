package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.HiringStage;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CandidateStageHistoryDTO {

    private HiringStage stage;
    private String remarks;
    private LocalDateTime updatedAt;
}
