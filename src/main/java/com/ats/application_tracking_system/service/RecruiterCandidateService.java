package com.ats.application_tracking_system.service;

import com.ats.application_tracking_system.dto.RecruiterCandidateRequestDTO;
import com.ats.application_tracking_system.dto.RecruiterCandidateResponseDTO;
import com.ats.application_tracking_system.dto.RecruiterCandidateUpdateDTO;
import com.ats.application_tracking_system.enums.UserRole;
import com.ats.application_tracking_system.exception.AccessDeniedException;
import com.ats.application_tracking_system.exception.ResourceNotFoundException;
import com.ats.application_tracking_system.model.JobOpening;
import com.ats.application_tracking_system.model.RecruiterCandidate;
import com.ats.application_tracking_system.model.User;
import com.ats.application_tracking_system.repository.JobOpeningRepository;
import com.ats.application_tracking_system.repository.RecruiterCandidateRepository;
import com.ats.application_tracking_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecruiterCandidateService {

    private final RecruiterCandidateRepository candidateRepo;
    private final JobOpeningRepository jobRepo;
    private final UserRepository userRepo;
    private final ResumeStorageService resumeStorageService;

    public RecruiterCandidateResponseDTO addCandidate(
            RecruiterCandidateRequestDTO dto,
            MultipartFile resume,
            String recruiterEmail) {

        User recruiter = getRecruiter(recruiterEmail);

        JobOpening job = jobRepo.findById(dto.getJobOpeningId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        RecruiterCandidate candidate = new RecruiterCandidate();
        candidate.setFullName(dto.getFullName());
        candidate.setEmail(dto.getEmail());
        candidate.setPhone(dto.getPhone());
        candidate.setJobOpening(job);
        candidate.setRecruiter(recruiter);

        if (resume != null && !resume.isEmpty()) {
            candidate.setResumePath(resumeStorageService.store(resume));
        }

        return map(candidateRepo.save(candidate));
    }

    public RecruiterCandidateResponseDTO updateCandidate(
            Long id,
            RecruiterCandidateUpdateDTO dto,
            String recruiterEmail) {

        RecruiterCandidate candidate = candidateRepo
                .findByIdAndRecruiter(id, getRecruiter(recruiterEmail))
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        if (dto.getFullName() != null)
            candidate.setFullName(dto.getFullName());

        if (dto.getPhone() != null)
            candidate.setPhone(dto.getPhone());

        if (dto.getStatus() != null)
            candidate.setStatus(dto.getStatus());

        return map(candidateRepo.save(candidate));
    }

    public Page<RecruiterCandidateResponseDTO> getCandidates(
            Pageable pageable,
            String recruiterEmail) {

        return candidateRepo
                .findByRecruiter(getRecruiter(recruiterEmail), pageable)
                .map(this::map);
    }

    private User getRecruiter(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (user.getRole() != UserRole.RECRUITER) {
            throw new AccessDeniedException("Recruiter access only");
        }
        return user;
    }

    private RecruiterCandidateResponseDTO map(RecruiterCandidate c) {
        RecruiterCandidateResponseDTO dto = new RecruiterCandidateResponseDTO();
        dto.setId(c.getId());
        dto.setFullName(c.getFullName());
        dto.setEmail(c.getEmail());
        dto.setPhone(c.getPhone());
        dto.setStatus(c.getStatus());
        dto.setJobOpeningId(c.getJobOpening().getId());
        dto.setResumePath(c.getResumePath());
        return dto;
    }
}

