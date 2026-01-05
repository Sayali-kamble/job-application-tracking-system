package com.ats.application_tracking_system.service;

import com.ats.application_tracking_system.dto.CandidateStageHistoryDTO;
import com.ats.application_tracking_system.dto.UpdateHiringStageRequestDTO;
import com.ats.application_tracking_system.enums.UserRole;
import com.ats.application_tracking_system.exception.AccessDeniedException;
import com.ats.application_tracking_system.exception.ResourceNotFoundException;
import com.ats.application_tracking_system.model.CandidateStageHistory;
import com.ats.application_tracking_system.model.RecruiterCandidate;
import com.ats.application_tracking_system.model.User;
import com.ats.application_tracking_system.repository.CandidateStageHistoryRepository;
import com.ats.application_tracking_system.repository.RecruiterCandidateRepository;
import com.ats.application_tracking_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HiringPipelineService {

    private final RecruiterCandidateRepository candidateRepo;
    private final CandidateStageHistoryRepository historyRepo;
    private final UserRepository userRepo;

    public void updateStage(
            Long candidateId,
            UpdateHiringStageRequestDTO dto,
            String recruiterEmail) {

        User recruiter = getRecruiter(recruiterEmail);

        RecruiterCandidate candidate = candidateRepo
                .findByIdAndRecruiter(candidateId, recruiter)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Candidate not found"));

        // Update current stage
        candidate.setCurrentStage(dto.getStage());
        candidateRepo.save(candidate);

        // Save stage history
        CandidateStageHistory history = new CandidateStageHistory();
        history.setCandidate(candidate);
        history.setStage(dto.getStage());
        history.setRemarks(dto.getRemarks());

        historyRepo.save(history);

        log.info("Candidate {} moved to stage {} by recruiter {}",
                candidateId, dto.getStage(), recruiterEmail);
    }

    public List<CandidateStageHistoryDTO> getStageHistory(
            Long candidateId,
            String recruiterEmail) {

        User recruiter = getRecruiter(recruiterEmail);

        RecruiterCandidate candidate = candidateRepo
                .findByIdAndRecruiter(candidateId, recruiter)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Candidate not found"));

        return historyRepo.findByCandidateOrderByUpdatedAtAsc(candidate)
                .stream()
                .map(this::map)
                .toList();
    }

    private User getRecruiter(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (user.getRole() != UserRole.RECRUITER) {
            throw new AccessDeniedException("Recruiter access only");
        }
        return user;
    }

    private CandidateStageHistoryDTO map(CandidateStageHistory h) {
        CandidateStageHistoryDTO dto = new CandidateStageHistoryDTO();
        dto.setStage(h.getStage());
        dto.setRemarks(h.getRemarks());
        dto.setUpdatedAt(h.getUpdatedAt());
        return dto;
    }
}

