package com.ats.application_tracking_system.service;

import com.ats.application_tracking_system.dto.CandidateApplicationResponseDTO;
import com.ats.application_tracking_system.dto.CandidateDashboardDTO;
import com.ats.application_tracking_system.dto.StatusCountDTO;
import com.ats.application_tracking_system.enums.ApplicationStatus;
import com.ats.application_tracking_system.exception.ResourceNotFoundException;
import com.ats.application_tracking_system.model.CandidateApplication;
import com.ats.application_tracking_system.model.User;
import com.ats.application_tracking_system.repository.CandidateApplicationRepository;
import com.ats.application_tracking_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandidateDashboardService {

    private final CandidateApplicationRepository repository;
    private final UserRepository userRepository;

    public CandidateDashboardDTO getDashboard(String email) {

        log.info("Fetching dashboard for candidate: {}", email);

        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        CandidateDashboardDTO dashboard = new CandidateDashboardDTO();

        // total applications
        dashboard.setTotalApplications(
                repository.countByCandidate(candidate)
        );

        // status wise count
        List<StatusCountDTO> statusCounts = Arrays.stream(ApplicationStatus.values())
                .map(status ->
                        new StatusCountDTO(
                                status,
                                repository.countByCandidateAndStatus(candidate, status)
                        )
                ).toList();

        dashboard.setStatusWiseCount(statusCounts);

        // recent applications
        List<CandidateApplicationResponseDTO> recentApps =
                repository.findTop5ByCandidateOrderByAppliedDateDesc(candidate)
                        .stream()
                        .map(this::mapToResponse)
                        .toList();

        dashboard.setRecentApplications(recentApps);

        return dashboard;
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
