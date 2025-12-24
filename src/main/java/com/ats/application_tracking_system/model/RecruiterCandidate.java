package com.ats.application_tracking_system.model;

import com.ats.application_tracking_system.enums.HiringStage;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Table(name = "recruiter_candidates")
public class RecruiterCandidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private RecruiterJob job;

    @Column(nullable = false)
    private String candidateName;

    private String email;
    private String phone;

    @Enumerated(EnumType.STRING)
    private HiringStage currentStage; // SCREENING, INTERVIEW, SELECTED

    private String resumeUrl;

    @Column(length = 1000)
    private String remarks;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
