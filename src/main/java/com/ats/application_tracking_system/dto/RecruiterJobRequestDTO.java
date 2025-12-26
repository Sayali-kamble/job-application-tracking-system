package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.JobStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RecruiterJobRequestDTO {
    @NotBlank(message = "Job title is required")
    private String jobTitle;
    private String department;
    private String location;
    private String employmentType;
    private JobStatus jobStatus;
}
