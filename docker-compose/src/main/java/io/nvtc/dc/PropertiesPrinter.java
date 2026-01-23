package io.nvtc.dc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.service.connection.ConnectionDetails;
import org.springframework.boot.jdbc.autoconfigure.JdbcConnectionDetails;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@DependsOnDatabaseInitialization
public class PropertiesPrinter {

    @Autowired
    private Map<String, ConnectionDetails> connectionDetails;

    @Autowired
    private DataSource dataSource; // Spring Boot auto-configured one

    @PostConstruct
    public void printThings() throws SQLException, JacksonException {
        printApplicationProperties();
        //printDataSource();
        printConnectionDetails();
    }

    public void printApplicationProperties() {
        try {
            var resource = new ClassPathResource("application.properties");

            if (!resource.exists()) {
                System.out.println("No application.properties found on classpath.");
                return;
            }

            try (var reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
                String content = reader.lines()
                        .filter(line -> line.contains("datasource"))
                        .collect(Collectors.joining("\n"));

                System.out.println("\n==============================");
                System.out.println("  DATASOURCE.PROPERTIES (in file)");
                System.out.println("==============================");
                System.out.println(content);
                System.out.println("==============================\n");
            }

        } catch (Exception ex) {
            System.out.println("Failed to print application.properties: " + ex.getMessage());
        }
    }

    public void printDataSource() throws SQLException {
        System.out.println("\n==============================");
        System.out.println("  DATASOURCE.PROPERTIES (detected)");
        System.out.println("==============================");
        System.out.println("DataSource URL = " + dataSource.getConnection().getMetaData().getURL());
        System.out.println("DataSource Driver = " + dataSource.getConnection().getMetaData().getDriverName());
        System.out.println("DataSource User = " + dataSource.getConnection().getMetaData().getUserName());
        System.out.println("==============================");
    }

    public void printConnectionDetails() throws JacksonException {
        Map<String, Object> result = new HashMap<>();

        connectionDetails.forEach((name, details) -> result.put(name, extractConnectionDetails(details)));

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        System.out.println("\n==============================");
        System.out.println("  CONNECTION DETAILS");
        System.out.println(mapper.writeValueAsString(result));
        System.out.println("==============================");
    }

    private Map<String, Object> extractConnectionDetails(ConnectionDetails details) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", details.getClass().getName());

        if (details instanceof JdbcConnectionDetails jdbc) {
            map.put("jdbcUrl", jdbc.getJdbcUrl());
            map.put("username", jdbc.getUsername());
            map.put("password", jdbc.getPassword());
            map.put("driverClassName", jdbc.getDriverClassName());
        }
        return map;
    }
}
