package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.HiringStage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateHiringStageRequestDTO {

    @NotNull
    private HiringStage stage;

    @Size(max = 1000)
    private String remarks;
}

