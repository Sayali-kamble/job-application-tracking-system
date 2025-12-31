package com.ats.application_tracking_system.service;

import com.ats.application_tracking_system.dto.CandidateApplicationRequestDTO;
import com.ats.application_tracking_system.dto.CandidateApplicationResponseDTO;
import com.ats.application_tracking_system.enums.ApplicationStatus;
import com.ats.application_tracking_system.exception.ResourceNotFoundException;
import com.ats.application_tracking_system.exception.UnauthorizedAccessException;
import com.ats.application_tracking_system.model.CandidateApplication;
import com.ats.application_tracking_system.model.User;
import com.ats.application_tracking_system.repository.CandidateApplicationRepository;
import com.ats.application_tracking_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;



@Service
@Slf4j
@RequiredArgsConstructor
public class CandidateApplicationService {

    private final CandidateApplicationRepository repository;
    private final UserRepository userRepository;

    // ================= CREATE =================
    public CandidateApplicationResponseDTO createApplication(
            CandidateApplicationRequestDTO dto,
            String email) {

        //Fetch logged-in candidate
        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with email: " + email));

        log.info("Creating application for candidate: {}", email);

        //Create entity
        CandidateApplication application = new CandidateApplication();
        application.setCandidate(candidate);
        application.setCompanyName(dto.getCompanyName());
        application.setJobRole(dto.getJobRole());
        application.setPlatform(dto.getPlatform());
        application.setStatus(dto.getStatus());
        application.setAppliedDate(dto.getAppliedDate());
        application.setLastUpdated(LocalDate.now());

        //Save
        CandidateApplication saved = repository.save(application);

        //Map to response DTO
        return mapToResponse(saved);
    }


    // ================= READ (PAGINATED) =================
    public Page<CandidateApplicationResponseDTO> getApplications(
            String email,
            int page,
            int size) {

        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found: " + email));

        log.info("Fetching applications for candidate: {}", email);

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("appliedDate").descending()
        );

        return repository
                .findByCandidate(candidate, pageable)
                .map(this::mapToResponse);
    }


    // ================= UPDATE =================
    public CandidateApplicationResponseDTO updateApplication(
            Long applicationId,
            CandidateApplicationRequestDTO dto,
            String email) {

        // Fetch candidate from DB
        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found: " + email));

        // Fetch application
        CandidateApplication application = repository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application not found: " + applicationId));

        // Ownership check
        if (!application.getCandidate().getId().equals(candidate.getId())) {
            log.warn("Unauthorized update attempt by {} on application {}",
                    email, applicationId);
            throw new UnauthorizedAccessException(
                    "You are not allowed to update this application");
        }

        log.info("Updating application {} by {}", applicationId, email);

        // Update fields
        application.setCompanyName(dto.getCompanyName());
        application.setJobRole(dto.getJobRole());
        application.setPlatform(dto.getPlatform());
        application.setStatus(dto.getStatus());
        application.setAppliedDate(dto.getAppliedDate());
        application.setLastUpdated(LocalDate.now());

        CandidateApplication updated = repository.save(application);

        return mapToResponse(updated);
    }



    // ================= DELETE =================
    public void deleteApplication(Long applicationId, String email) {

        // Fetch candidate from DB
        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found: " + email));

        // Fetch application
        CandidateApplication application = repository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application not found: " + applicationId));

        // Ownership check
        if (!application.getCandidate().getId().equals(candidate.getId())) {
            log.warn("Unauthorized delete attempt by {} on application {}",
                    email, applicationId);
            throw new UnauthorizedAccessException(
                    "You are not allowed to delete this application");
        }

        log.info("Deleting application {} by {}", applicationId, email);

        repository.delete(application);
    }

    /*public void deleteApplication(Long applicationId, User candidate) {

        CandidateApplication application = repository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application not found"));

        if (!application.getCandidate().getId().equals(candidate.getId())) {
            log.warn("Unauthorized delete attempt by {}", candidate.getEmail());
            throw new UnauthorizedAccessException("You cannot delete this application");
        }

        log.warn("Deleting application {} by {}", applicationId, candidate.getEmail());
        repository.delete(application);
    }*/

    // ================= FILTER BY STATUS=================
    public Page<CandidateApplicationResponseDTO> getByStatus(
            String email,
            ApplicationStatus status,
            int page,
            int size) {

        // Fetch candidate
        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found: " + email));

        log.info("Fetching {} applications for {}", status, email);

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("appliedDate").descending()
        );

        return repository
                .findByCandidateAndStatus(candidate, status, pageable)
                .map(this::mapToResponse);
    }

    // ================= MAPPER =================
    private CandidateApplicationResponseDTO mapToResponse(
            CandidateApplication application) {

        CandidateApplicationResponseDTO dto =
                new CandidateApplicationResponseDTO();

        dto.setId(application.getId());
        dto.setCompanyName(application.getCompanyName());
        dto.setJobRole(application.getJobRole());
        dto.setPlatform(application.getPlatform());
        dto.setStatus(application.getStatus());
        dto.setAppliedDate(application.getAppliedDate());

        return dto;
    }
}