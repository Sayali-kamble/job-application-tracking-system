package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.JobStatus;
import lombok.Data;

@Data
public class RecruiterJobResponseDTO {
    private Long id;
    private String jobTitle;
    private String department;
    private String location;
    private JobStatus jobStatus;
}
