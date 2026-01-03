package com.ats.application_tracking_system.dto;

import java.util.List;

public class CandidateDashboardDTO {
    private long totalApplications;

    private List<StatusCountDTO> statusWiseCount;

    private List<CandidateApplicationResponseDTO> recentApplications;

    public long getTotalApplications() {
        return totalApplications;
    }

    public List<StatusCountDTO> getStatusWiseCount() {
        return statusWiseCount;
    }

    public List<CandidateApplicationResponseDTO> getRecentApplications() {
        return recentApplications;
    }

    public void setTotalApplications(long totalApplications) {
        this.totalApplications = totalApplications;
    }

    public void setRecentApplications(List<CandidateApplicationResponseDTO> recentApplications) {
        this.recentApplications = recentApplications;
    }

    public void setStatusWiseCount(List<StatusCountDTO> statusWiseCount) {
        this.statusWiseCount = statusWiseCount;
    }
}
