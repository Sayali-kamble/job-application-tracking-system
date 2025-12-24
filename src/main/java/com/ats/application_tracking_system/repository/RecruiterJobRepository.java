package com.ats.application_tracking_system.repository;

import com.ats.application_tracking_system.enums.JobStatus;
import com.ats.application_tracking_system.model.RecruiterJob;
import com.ats.application_tracking_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruiterJobRepository
        extends JpaRepository<RecruiterJob, Long> {

    List<RecruiterJob> findByRecruiter(User recruiter);

    List<RecruiterJob> findByRecruiterAndJobStatus(
            User recruiter,
            JobStatus jobStatus
    );
}
