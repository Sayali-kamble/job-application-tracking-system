package com.ats.application_tracking_system.repository;

import com.ats.application_tracking_system.model.CandidateStageHistory;
import com.ats.application_tracking_system.model.RecruiterCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateStageHistoryRepository
        extends JpaRepository<CandidateStageHistory, Long> {

    List<CandidateStageHistory> findByCandidateOrderByUpdatedAtAsc(
            RecruiterCandidate candidate
    );
}

