package com.ats.application_tracking_system.service;

import com.ats.application_tracking_system.dto.JobSummaryDTO;
import com.ats.application_tracking_system.dto.RecruiterDashboardDTO;
import com.ats.application_tracking_system.dto.StageCountDTO;
import com.ats.application_tracking_system.enums.JobStatus;
import com.ats.application_tracking_system.repository.JobOpeningRepository;
import com.ats.application_tracking_system.repository.RecruiterCandidateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecruiterDashboardService {

    private final JobOpeningRepository jobRepo;
    private final RecruiterCandidateRepository candidateRepo;

    public RecruiterDashboardDTO getDashboard(String recruiterEmail) {

        log.info("Fetching dashboard for recruiter: {}", recruiterEmail);

        long openPositions =
                jobRepo.countByRecruiterEmailAndStatus(
                        recruiterEmail, JobStatus.OPEN);

        List<JobSummaryDTO> perJob =
                candidateRepo.countCandidatesPerJob(recruiterEmail);

        List<StageCountDTO> byStage =
                candidateRepo.countByStage(recruiterEmail);

        return RecruiterDashboardDTO.builder()
                .openPositions(openPositions)
                .candidatesPerJob(perJob)
                .candidatesByStage(byStage)
                .build();
    }
}

