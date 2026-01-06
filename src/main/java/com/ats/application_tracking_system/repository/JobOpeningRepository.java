package com.ats.application_tracking_system.repository;

import com.ats.application_tracking_system.enums.JobStatus;
import com.ats.application_tracking_system.model.JobOpening;
import com.ats.application_tracking_system.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Long> {

    Page<JobOpening> findByRecruiter(User recruiter, Pageable pageable);

    Optional<JobOpening> findByIdAndRecruiter(Long id, User recruiter);

    long countByRecruiterEmailAndStatus(String recruiterEmail, JobStatus status);
}
