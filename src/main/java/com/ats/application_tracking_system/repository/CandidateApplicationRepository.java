package com.ats.application_tracking_system.repository;

import com.ats.application_tracking_system.enums.ApplicationStatus;
import com.ats.application_tracking_system.model.CandidateApplication;
import com.ats.application_tracking_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateApplicationRepository
        extends JpaRepository<CandidateApplication, Long> {

    List<CandidateApplication> findByCandidate(User candidate);

    List<CandidateApplication> findByCandidateAndStatus(
            User candidate,
            ApplicationStatus status
    );
}
