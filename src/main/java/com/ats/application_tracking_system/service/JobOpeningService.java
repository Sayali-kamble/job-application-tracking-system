package com.ats.application_tracking_system.service;

import com.ats.application_tracking_system.dto.JobOpeningRequestDTO;
import com.ats.application_tracking_system.dto.JobOpeningResponseDTO;
import com.ats.application_tracking_system.enums.JobStatus;
import com.ats.application_tracking_system.enums.UserRole;
import com.ats.application_tracking_system.exception.AccessDeniedException;
import com.ats.application_tracking_system.exception.ResourceNotFoundException;
import com.ats.application_tracking_system.model.JobOpening;
import com.ats.application_tracking_system.model.User;
import com.ats.application_tracking_system.repository.JobOpeningRepository;
import com.ats.application_tracking_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobOpeningService {

    private final JobOpeningRepository jobOpeningRepository;
    private final UserRepository userRepository;

    public JobOpeningResponseDTO createJob(
            JobOpeningRequestDTO dto,
            String recruiterEmail) {

        log.info("Creating job opening by recruiter: {}", recruiterEmail);

        User recruiter = getRecruiter(recruiterEmail);

        JobOpening job = new JobOpening();
        job.setJobTitle(dto.getJobTitle());
        job.setDepartment(dto.getDepartment());
        job.setLocation(dto.getLocation());
        job.setEmploymentType(dto.getEmploymentType());
        job.setRecruiter(recruiter);

        JobOpening saved = jobOpeningRepository.save(job);
        return mapToResponse(saved);
    }

    public JobOpeningResponseDTO updateJob(
            Long jobId,
            JobOpeningRequestDTO dto,
            String recruiterEmail) {

        User recruiter = getRecruiter(recruiterEmail);

        JobOpening job = jobOpeningRepository
                .findByIdAndRecruiter(jobId, recruiter)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job opening not found"));

        job.setJobTitle(dto.getJobTitle());
        job.setDepartment(dto.getDepartment());
        job.setLocation(dto.getLocation());
        job.setEmploymentType(dto.getEmploymentType());

        return mapToResponse(jobOpeningRepository.save(job));
    }

    public void closeJob(Long jobId, String recruiterEmail) {

        User recruiter = getRecruiter(recruiterEmail);

        JobOpening job = jobOpeningRepository
                .findByIdAndRecruiter(jobId, recruiter)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job opening not found"));

        job.setStatus(JobStatus.CLOSED);
        jobOpeningRepository.save(job);

        log.info("Job {} closed by recruiter {}", jobId, recruiterEmail);
    }

    public Page<JobOpeningResponseDTO> getMyJobs(
            String recruiterEmail,
            Pageable pageable) {

        User recruiter = getRecruiter(recruiterEmail);

        return jobOpeningRepository
                .findByRecruiter(recruiter, pageable)
                .map(this::mapToResponse);
    }

    private User getRecruiter(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (user.getRole() != UserRole.RECRUITER) {
            throw new AccessDeniedException("Only recruiters allowed");
        }
        return user;
    }

    private JobOpeningResponseDTO mapToResponse(JobOpening job) {
        JobOpeningResponseDTO dto = new JobOpeningResponseDTO();
        dto.setId(job.getId());
        dto.setJobTitle(job.getJobTitle());
        dto.setDepartment(job.getDepartment());
        dto.setLocation(job.getLocation());
        dto.setEmploymentType(job.getEmploymentType());
        dto.setStatus(job.getStatus());
        return dto;
    }
}

