package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.HiringStage;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecruiterStageUpdateDTO {
    @NotNull(message = "New hiring stage is required")
    private HiringStage newStage;
    private String remarks;

}
