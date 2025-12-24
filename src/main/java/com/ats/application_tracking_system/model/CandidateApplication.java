package com.ats.application_tracking_system.model;

import com.ats.application_tracking_system.enums.ApplicationPlatform;
import com.ats.application_tracking_system.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Data
@Table(name = "candidate_applications")
public class CandidateApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private User candidate;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String jobRole;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationPlatform platform; // Naukri, LinkedIn, Referral

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status; // APPLIED, NO_RESPONSE, SELECTED, etc.

    private LocalDate appliedDate;

    private LocalDate lastUpdated;

}
