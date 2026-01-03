package com.ats.application_tracking_system.controller;

import com.ats.application_tracking_system.dto.CandidateDashboardDTO;
import com.ats.application_tracking_system.service.CandidateDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/candidate/dashboard")
@RequiredArgsConstructor
public class CandidateDashboardController {

    private final CandidateDashboardService dashboardService;

    @GetMapping
    public ResponseEntity<CandidateDashboardDTO> getDashboard(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                dashboardService.getDashboard(email)
        );
    }
}

