package com.ats.application_tracking_system.service;

import com.ats.application_tracking_system.dto.ApplicationSearchRequest;
import com.ats.application_tracking_system.dto.CandidateApplicationResponseDTO;
import com.ats.application_tracking_system.exception.ResourceNotFoundException;
import com.ats.application_tracking_system.model.CandidateApplication;
import com.ats.application_tracking_system.model.User;
import com.ats.application_tracking_system.repository.CandidateApplicationRepository;
import com.ats.application_tracking_system.repository.UserRepository;
import com.ats.application_tracking_system.specification.CandidateApplicationSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandidateSearchService {

    private final CandidateApplicationRepository repository;
    private final UserRepository userRepository;

    public Page<CandidateApplicationResponseDTO> searchApplications(
            ApplicationSearchRequest request,
            Pageable pageable,
            String email) {

        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Specification<CandidateApplication> spec =
                CandidateApplicationSpecification.filter(candidate, request);

        return repository.findAll(spec, pageable)
                .map(this::mapToResponse);
    }

    public CandidateApplicationResponseDTO getApplicationById(Long id, String email) {

        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        CandidateApplication application = repository
                .findByIdAndCandidate(id, candidate)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application not found"));

        return mapToResponse(application);
    }

    private CandidateApplicationResponseDTO mapToResponse(CandidateApplication app) {
        CandidateApplicationResponseDTO dto = new CandidateApplicationResponseDTO();
        dto.setId(app.getId());
        dto.setCompanyName(app.getCompanyName());
        dto.setJobRole(app.getJobRole());
        dto.setPlatform(app.getPlatform());
        dto.setStatus(app.getStatus());
        dto.setAppliedDate(app.getAppliedDate());
        return dto;
    }
}
