package io.nvtc.tc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.boot.autoconfigure.service.connection.ConnectionDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectionDetailsController {

    private final Map<String, ConnectionDetails> connectionDetails;
    
    public ConnectionDetailsController(Map<String, ConnectionDetails> connectionDetails) {
        this.connectionDetails = connectionDetails;
    }
    
    @GetMapping("/demo/connection-details")
    public Map<String, Object> showConnectionDetails() {
        Map<String, Object> result = new HashMap<>();
        
        connectionDetails.forEach((name, details) -> {
            result.put(name, extractConnectionDetails(details));
        });
        
        return result;
    }
    
    private Map<String, Object> extractConnectionDetails(ConnectionDetails details) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", details.getClass().getName());
        
        // Extract specific properties based on ConnectionDetails type
        if (details instanceof JdbcConnectionDetails) {
            JdbcConnectionDetails jdbc = (JdbcConnectionDetails) details;

            map.put("jdbcUrl", jdbc.getJdbcUrl());
            map.put("username", jdbc.getUsername());
            map.put("password", jdbc.getPassword());
            map.put("driverClassName", jdbc.getDriverClassName());
            
            // Don't include password for security reasons
        }        
        return map;
    }
}