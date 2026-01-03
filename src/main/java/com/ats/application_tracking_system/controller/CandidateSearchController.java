package com.ats.application_tracking_system.controller;

import com.ats.application_tracking_system.dto.ApplicationSearchRequest;
import com.ats.application_tracking_system.dto.CandidateApplicationResponseDTO;
import com.ats.application_tracking_system.service.CandidateSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate/applications")
@RequiredArgsConstructor
public class CandidateSearchController {

    private final CandidateSearchService searchService;

    @PostMapping("/search")
    public ResponseEntity<Page<CandidateApplicationResponseDTO>> search(
            @RequestBody ApplicationSearchRequest request,
            Pageable pageable,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                searchService.searchApplications(request, pageable, email)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateApplicationResponseDTO> getById(
            @PathVariable Long id,
            Authentication authentication) {

        String email = authentication.getName();

        // reuse service from earlier module
        return ResponseEntity.ok(
                searchService.getApplicationById(id, email)
        );
    }
}

