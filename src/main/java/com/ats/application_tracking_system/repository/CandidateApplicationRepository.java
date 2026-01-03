package com.ats.application_tracking_system.repository;

import com.ats.application_tracking_system.enums.ApplicationStatus;
import com.ats.application_tracking_system.model.CandidateApplication;
import com.ats.application_tracking_system.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CandidateApplicationRepository
        extends JpaRepository<CandidateApplication, Long>, JpaSpecificationExecutor<CandidateApplication> {


    Page<CandidateApplication> findByCandidate(User candidate, Pageable pageable);

    Page<CandidateApplication> findByCandidateAndStatus(
            User candidate,
            ApplicationStatus status,
            Pageable pageable
    );
    Optional<CandidateApplication> findByIdAndCandidate(Long id, User candidate);

    long countByCandidateAndStatus(User candidate, ApplicationStatus status);

    long countByCandidate(User candidate);

    List<CandidateApplication> findTop5ByCandidateOrderByAppliedDateDesc(User candidate);
}
