package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CandidateStatusUpdateDTO {
    @NotNull(message = "New status is required")
    private ApplicationStatus newStatus;
}
