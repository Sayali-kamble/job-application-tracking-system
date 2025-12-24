package com.ats.application_tracking_system.repository;

import com.ats.application_tracking_system.enums.HiringStage;
import com.ats.application_tracking_system.model.RecruiterCandidate;
import com.ats.application_tracking_system.model.RecruiterJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruiterCandidateRepository
        extends JpaRepository<RecruiterCandidate, Long> {

    List<RecruiterCandidate> findByJob(RecruiterJob job);

    List<RecruiterCandidate> findByJobAndCurrentStage(
            RecruiterJob job,
            HiringStage currentStage
    );
}
