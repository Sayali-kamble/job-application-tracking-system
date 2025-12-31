package com.ats.application_tracking_system.service;

import com.ats.application_tracking_system.dto.CandidateApplicationResponseDTO;
import com.ats.application_tracking_system.dto.CandidateStatusUpdateDTO;
import com.ats.application_tracking_system.enums.ApplicationStatus;
import com.ats.application_tracking_system.exception.ResourceNotFoundException;
import com.ats.application_tracking_system.exception.UnauthorizedAccessException;
import com.ats.application_tracking_system.model.CandidateApplication;
import com.ats.application_tracking_system.model.User;
import com.ats.application_tracking_system.repository.CandidateApplicationRepository;
import com.ats.application_tracking_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ApplicationStatusService {

    private final CandidateApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    @Value("${application.no-response.days}")
    private int noResponseDays;

    public ApplicationStatusService(
            CandidateApplicationRepository applicationRepository,
            UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
    }

    // ================= MANUAL STATUS UPDATE =================
    @Transactional
    public CandidateApplicationResponseDTO updateStatus(
            Long applicationId,
            CandidateStatusUpdateDTO dto,
            String email) {

        log.info("Status update request for application {} by {}", applicationId, email);

        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Candidate not found"));

        CandidateApplication application = applicationRepository
                .findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application not found"));

        // Ownership validation
        if (!application.getCandidate().getId().equals(candidate.getId())) {
            log.warn("Unauthorized update attempt by {}", email);
            throw new UnauthorizedAccessException("Access denied");
        }

        application.setStatus(dto.getNewStatus());
        application.setLastUpdated(LocalDate.now());

        CandidateApplication saved =
                applicationRepository.save(application);

        log.info("Application {} updated to {}", applicationId, dto.getNewStatus());

        return mapToResponse(saved);
    }

    // ================= AUTO NO_RESPONSE =================
    public void autoMarkNoResponse() {

        LocalDate thresholdDate =
                LocalDate.now().minusDays(noResponseDays);

        List<CandidateApplication> applications =
                applicationRepository.findAll();

        int count = 0;

        for (CandidateApplication app : applications) {

            if (app.getStatus() != ApplicationStatus.NO_RESPONSE &&
                    (app.getLastUpdated() == null ||
                            app.getLastUpdated().isBefore(thresholdDate))) {

                app.setStatus(ApplicationStatus.NO_RESPONSE);
                app.setLastUpdated(LocalDate.now());
                count++;
            }
        }

        applicationRepository.saveAll(applications);

        log.info("{} applications auto-marked as NO_RESPONSE", count);
    }

    // ================= ENTITY → DTO =================
    private CandidateApplicationResponseDTO mapToResponse(
            CandidateApplication app) {

        CandidateApplicationResponseDTO dto =
                new CandidateApplicationResponseDTO();

        dto.setId(app.getId());
        dto.setCompanyName(app.getCompanyName());
        dto.setJobRole(app.getJobRole());
        dto.setPlatform(app.getPlatform());
        dto.setStatus(app.getStatus());
        dto.setAppliedDate(app.getAppliedDate());

        return dto;
    }
}