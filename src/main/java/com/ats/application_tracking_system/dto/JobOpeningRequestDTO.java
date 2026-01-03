package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.EmploymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobOpeningRequestDTO {

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Employment type is required")
    private EmploymentType employmentType;
}

