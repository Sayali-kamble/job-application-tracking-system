package com.ats.application_tracking_system.controller;

import com.ats.application_tracking_system.dto.RecruiterCandidateRequestDTO;
import com.ats.application_tracking_system.dto.RecruiterCandidateResponseDTO;
import com.ats.application_tracking_system.dto.RecruiterCandidateUpdateDTO;
import com.ats.application_tracking_system.service.RecruiterCandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/recruiter/candidates")
@RequiredArgsConstructor
public class RecruiterCandidateController {

    private final RecruiterCandidateService service;

    /*@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RecruiterCandidateResponseDTO> addCandidate(
            @RequestPart("data") @Valid RecruiterCandidateRequestDTO dto,
            @RequestPart(value = "resume", required = false) MultipartFile resume,
            Authentication authentication) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addCandidate(dto, resume, authentication.getName()));
    }*/

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<RecruiterCandidateResponseDTO> addCandidate(
            @Valid @RequestPart("candidate") RecruiterCandidateRequestDTO dto,
            @RequestPart(value = "resume", required = false) MultipartFile resume,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                service.addCandidate(dto, resume, authentication.getName())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecruiterCandidateResponseDTO> updateCandidate(
            @PathVariable Long id,
            @RequestBody RecruiterCandidateUpdateDTO dto,
            Authentication authentication) {

        return ResponseEntity.ok(
                service.updateCandidate(id, dto, authentication.getName()));
    }

    @GetMapping
    public ResponseEntity<Page<RecruiterCandidateResponseDTO>> getCandidates(
            Pageable pageable,
            Authentication authentication) {

        return ResponseEntity.ok(
                service.getCandidates(pageable, authentication.getName()));
    }
}

