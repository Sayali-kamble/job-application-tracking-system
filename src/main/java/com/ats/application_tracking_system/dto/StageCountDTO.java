package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.HiringStage;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StageCountDTO {
    private HiringStage stage;
    private Long count;
}