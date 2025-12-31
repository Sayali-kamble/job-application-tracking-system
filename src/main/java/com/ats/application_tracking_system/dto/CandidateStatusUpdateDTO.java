package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


public class CandidateStatusUpdateDTO {
    @NotNull(message = "New status is required")
    private ApplicationStatus newStatus;

    public ApplicationStatus getNewStatus() {
        return newStatus;
    }

    public void setStatus(ApplicationStatus status) {
        this.newStatus = status;
    }
}
