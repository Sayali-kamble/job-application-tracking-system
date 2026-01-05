package com.ats.application_tracking_system.model;

import com.ats.application_tracking_system.enums.HiringStage;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "candidate_stage_history")
@Data
public class CandidateStageHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private RecruiterCandidate candidate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HiringStage stage;

    @Column(length = 1000)
    private String remarks;

    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        this.updatedAt = LocalDateTime.now();
    }
}

