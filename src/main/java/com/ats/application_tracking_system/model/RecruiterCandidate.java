package com.ats.application_tracking_system.model;

import com.ats.application_tracking_system.dto.CandidatePipelineStatus;
import com.ats.application_tracking_system.enums.HiringStage;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "recruiter_candidates")
@Getter
@Setter
@ToString(exclude = {"jobOpening", "recruiter"})
public class RecruiterCandidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "resume_path")
    private String resumePath;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CandidatePipelineStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private JobOpening jobOpening;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiter_id", nullable = false)
    private User recruiter;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = CandidatePipelineStatus.APPLIED;
        }
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

/*
@Entity
@Table(name = "recruiter_candidates")
@Data
public class RecruiterCandidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "candidate_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    private String phone;

    private String resumePath; // stored file path or S3 URL

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CandidatePipelineStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private JobOpening jobOpening;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private User recruiter;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = CandidatePipelineStatus.APPLIED;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
*/
