package com.ats.application_tracking_system.controller;

import com.ats.application_tracking_system.dto.CandidateApplicationResponseDTO;
import com.ats.application_tracking_system.dto.CandidateStatusUpdateDTO;
import com.ats.application_tracking_system.model.CandidateApplication;
import com.ats.application_tracking_system.model.User;
import com.ats.application_tracking_system.service.ApplicationStatusService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate/applications")
public class ApplicationStatusController {

    private final ApplicationStatusService service;

    public ApplicationStatusController(ApplicationStatusService service) {
        this.service = service;
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CandidateApplicationResponseDTO> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody CandidateStatusUpdateDTO dto,
            Authentication authentication) {

        String email = authentication.getName();

        CandidateApplicationResponseDTO response =
                service.updateStatus(id, dto, email);

        return ResponseEntity.ok(response);
    }
}
