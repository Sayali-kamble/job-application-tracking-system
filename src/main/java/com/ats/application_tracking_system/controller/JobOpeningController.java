package com.ats.application_tracking_system.controller;

import com.ats.application_tracking_system.dto.JobOpeningRequestDTO;
import com.ats.application_tracking_system.dto.JobOpeningResponseDTO;
import com.ats.application_tracking_system.service.JobOpeningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recruiter/jobs")
@RequiredArgsConstructor
public class JobOpeningController {

    private final JobOpeningService jobOpeningService;

    @PostMapping
    public ResponseEntity<JobOpeningResponseDTO> createJob(
            @Valid @RequestBody JobOpeningRequestDTO dto,
            Authentication authentication) {

        String email = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobOpeningService.createJob(dto, email));
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<JobOpeningResponseDTO> updateJob(
            @PathVariable Long jobId,
            @Valid @RequestBody JobOpeningRequestDTO dto,
            Authentication authentication) {

        String email = authentication.getName();
        return ResponseEntity.ok(
                jobOpeningService.updateJob(jobId, dto, email));
    }

    @PutMapping("/{jobId}/close")
    public ResponseEntity<Void> closeJob(
            @PathVariable Long jobId,
            Authentication authentication) {

        jobOpeningService.closeJob(jobId, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<JobOpeningResponseDTO>> getMyJobs(
            Pageable pageable,
            Authentication authentication) {

        return ResponseEntity.ok(
                jobOpeningService.getMyJobs(authentication.getName(), pageable));
    }
}
