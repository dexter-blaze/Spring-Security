package com.devtools.solution.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Endpoint(id = "database")
@Component
public class CustomEndpoint1 {
    Map<String, String> map = new HashMap<>();

    @ReadOperation
    public Map<String, String> getDbInfo() {
        map.put("database","MySQL");
        return map;
    }
}
