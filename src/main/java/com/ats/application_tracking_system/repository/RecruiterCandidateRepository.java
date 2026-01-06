package com.ats.application_tracking_system.repository;

import com.ats.application_tracking_system.dto.JobSummaryDTO;
import com.ats.application_tracking_system.dto.StageCountDTO;
import com.ats.application_tracking_system.enums.HiringStage;
import com.ats.application_tracking_system.enums.JobStatus;
import com.ats.application_tracking_system.model.JobOpening;
import com.ats.application_tracking_system.model.RecruiterCandidate;
import com.ats.application_tracking_system.model.RecruiterJob;
import com.ats.application_tracking_system.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecruiterCandidateRepository
        extends JpaRepository<RecruiterCandidate, Long> {

    Page<RecruiterCandidate> findByRecruiter(User recruiter, Pageable pageable);

    Page<RecruiterCandidate> findByJobOpening(JobOpening jobOpening, Pageable pageable);

    Optional<RecruiterCandidate> findByIdAndRecruiter(Long id, User recruiter);

    @Query("""
        SELECT new com.ats.application_tracking_system.dto.JobSummaryDTO(
            jc.jobOpening.id,
            jc.jobOpening.jobTitle,
            COUNT(jc)
        )
        FROM RecruiterCandidate jc
        WHERE jc.recruiter.email = :email
        GROUP BY jc.jobOpening.id, jc.jobOpening.jobTitle
    """)
    List<JobSummaryDTO> countCandidatesPerJob(@Param("email") String email);


    @Query("""
        SELECT new com.ats.application_tracking_system.dto.StageCountDTO(
            jc.currentStage,
            COUNT(jc)
        )
        FROM RecruiterCandidate jc
        WHERE jc.recruiter.email = :email
        GROUP BY jc.currentStage
    """)
    List<StageCountDTO> countByStage(@Param("email") String email);

    long countByRecruiterEmailAndStatus(String recruiterEmail, JobStatus status);


    Page<RecruiterCandidate> findByRecruiterEmailAndJobOpeningIdAndCurrentStage(
            String email,
            Long jobId,
            HiringStage currentStage,
            Pageable pageable
    );
}
