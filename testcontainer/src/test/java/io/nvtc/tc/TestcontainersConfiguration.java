package io.nvtc.tc;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;


@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	PostgreSQLContainer postgresFirstTestContainer() {
		return new PostgreSQLContainer(DockerImageName.parse("postgres:latest"));

	}

	@Bean
	@ServiceConnection
	PostgreSQLContainer postgresSecondTestContainer() {
		return new PostgreSQLContainer(DockerImageName.parse("postgres:latest"));

	}

}
