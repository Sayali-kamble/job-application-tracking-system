package com.ats.application_tracking_system.config;

import com.ats.application_tracking_system.service.ApplicationStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Slf4j
public class NoResponseScheduler {

    private final ApplicationStatusService service;

    public NoResponseScheduler(ApplicationStatusService service) {
        this.service = service;
    }

    // Runs daily at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void markNoResponseApplications() {

        log.info("NO_RESPONSE scheduler started");
        service.autoMarkNoResponse();
    }
}
