package com.ats.application_tracking_system.controller;

import com.ats.application_tracking_system.dto.RecruiterDashboardDTO;
import com.ats.application_tracking_system.service.RecruiterDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recruiter/dashboard")
@RequiredArgsConstructor
public class RecruiterDashboardController {

    private final RecruiterDashboardService service;

    @GetMapping
    public ResponseEntity<RecruiterDashboardDTO> dashboard(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(service.getDashboard(email));
    }
}
