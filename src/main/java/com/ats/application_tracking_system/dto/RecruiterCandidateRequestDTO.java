package com.ats.application_tracking_system.dto;

import com.ats.application_tracking_system.enums.HiringStage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecruiterCandidateRequestDTO {
    @NotBlank
    private String fullName;

    @Email
    @NotBlank
    private String email;

    private String phone;

    @NotNull
    private Long jobOpeningId;
}
