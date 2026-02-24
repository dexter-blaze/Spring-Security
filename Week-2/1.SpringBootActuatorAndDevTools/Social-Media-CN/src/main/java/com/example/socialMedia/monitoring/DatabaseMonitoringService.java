package com.example.socialMedia.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseMonitoringService implements HealthIndicator {
    private final static String DATABASE_NAME = "Connections Database";

    @Override
    public Health health() {
        if (isDatabaseHealthy()) {
            return Health.up().withDetail(DATABASE_NAME, "is up and running!").build();
        } else {
            return Health.down().withDetail(DATABASE_NAME, "is down and not working!").build();
        }
    }

    private boolean isDatabaseHealthy() {
        return false;
    }
}