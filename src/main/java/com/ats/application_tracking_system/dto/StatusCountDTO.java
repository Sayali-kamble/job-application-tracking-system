package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

public class StatusCountDTO {
    public StatusCountDTO(ApplicationStatus status,long count) {
        this.status = status;
        this.count = count;
    }

    private ApplicationStatus status;
    private long count;

    public ApplicationStatus getStatus() {
        return status;
    }

    public long getCount() {
        return count;
    }
}