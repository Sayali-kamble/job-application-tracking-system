package com.ats.application_tracking_system.repository;

import com.ats.application_tracking_system.enums.HiringStage;
import com.ats.application_tracking_system.model.JobOpening;
import com.ats.application_tracking_system.model.RecruiterCandidate;
import com.ats.application_tracking_system.model.RecruiterJob;
import com.ats.application_tracking_system.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecruiterCandidateRepository
        extends JpaRepository<RecruiterCandidate, Long> {

    Page<RecruiterCandidate> findByRecruiter(User recruiter, Pageable pageable);

    Page<RecruiterCandidate> findByJobOpening(JobOpening jobOpening, Pageable pageable);

    Optional<RecruiterCandidate> findByIdAndRecruiter(Long id, User recruiter);
}
