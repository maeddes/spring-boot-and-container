package com.planetexpress.deliveries.integration;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class DeliveryDatabaseTest {

	@Container
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
		.withDatabaseName("galaxy")
		.withUsername("fry")
		.withPassword("bender");

	@Test
	void databaseShouldBeUpAndRunning() throws Exception {
		try (Connection conn = DriverManager.getConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
			 Statement stmt = conn.createStatement()) {

			stmt.execute("CREATE TABLE deliveries (id SERIAL PRIMARY KEY, planet VARCHAR(255))");
			stmt.execute("INSERT INTO deliveries (planet) VALUES ('Mars')");
			ResultSet rs = stmt.executeQuery("SELECT planet FROM deliveries");

			assertTrue(rs.next());
			assertEquals("Mars", rs.getString("planet"));
		}
	}
}
