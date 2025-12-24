package com.ats.application_tracking_system.model;

import com.ats.application_tracking_system.enums.JobStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "recruiter_jobs")
public class RecruiterJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private User recruiter;

    @Column(nullable = false)
    private String jobTitle;

    private String department;
    private String location;
    private String employmentType;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus; // OPEN / CLOSED

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
