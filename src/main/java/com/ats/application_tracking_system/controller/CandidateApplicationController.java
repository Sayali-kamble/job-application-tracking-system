package com.ats.application_tracking_system.controller;

import com.ats.application_tracking_system.dto.CandidateApplicationRequestDTO;
import com.ats.application_tracking_system.dto.CandidateApplicationResponseDTO;
import com.ats.application_tracking_system.enums.ApplicationStatus;
import com.ats.application_tracking_system.model.User;
import com.ats.application_tracking_system.service.CandidateApplicationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/candidate/applications")
public class CandidateApplicationController {

    private final CandidateApplicationService service;

    public CandidateApplicationController(
            CandidateApplicationService service) {
        this.service = service;
    }

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<CandidateApplicationResponseDTO> createApplication(
            @Valid @RequestBody CandidateApplicationRequestDTO dto,
            Authentication authentication) {

        // Extract email from JWT / Spring Security context
        String email = authentication.getName();

        // Call service with email (service fetches User entity)
        CandidateApplicationResponseDTO response = service.createApplication(dto, email);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // ================= READ (PAGINATED) =================
    @GetMapping
    public Page<CandidateApplicationResponseDTO> getApplications(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        String email = authentication.getName(); // from JWT
        return service.getApplications(email, page, size);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<CandidateApplicationResponseDTO> updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody CandidateApplicationRequestDTO dto,
            Authentication authentication) {

        // Extract email from JWT
        String email = authentication.getName();

        CandidateApplicationResponseDTO response =
                service.updateApplication(id, dto, email);

        return ResponseEntity.ok(response);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(
            @PathVariable Long id,
            Authentication authentication) {

        // Extract email from JWT
        String email = authentication.getName();

        service.deleteApplication(id, email);

        return ResponseEntity.noContent().build(); // 204 No Content
    }

    /*@DeleteMapping("/{id}")
    public void deleteApplication(
            @PathVariable Long id,
            @AuthenticationPrincipal User user) {

        service.deleteApplication(id, user);
    }
*/
    // ================= FILTER BY STATUS =================
    @GetMapping("/status/{status}")
    public Page<CandidateApplicationResponseDTO> getByStatus(
            @PathVariable ApplicationStatus status,
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        String email = authentication.getName();

        return service.getByStatus(email, status, page, size);
    }
    /*@GetMapping("/status/{status}")
    public Page<CandidateApplicationResponseDTO> getByStatus(
            @PathVariable ApplicationStatus status,
            @AuthenticationPrincipal User user,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return service.getByStatus(user, status, page, size);
    }*/
}
