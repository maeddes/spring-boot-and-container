package com.example.demo;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.SocatContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    SocatContainer socat() {
        return new SocatContainer(DockerImageName.parse("alpine/socat:1.8.0.3"))
                .withTarget(80, "model-runner.docker.internal");
    }
    
    @Bean
    DynamicPropertyRegistrar properties(SocatContainer socat) {
        return (registrar) -> {
            registrar.add("spring.ai.openai.base-url", () -> "http://%s:%d/engines".formatted(socat.getHost(), socat.getMappedPort(80)));
            registrar.add("spring.ai.openai.api-key", () -> "test-api-key");
            registrar.add("spring.ai.openai.chat.options.model", () -> "ai/gemma3");
        };
    }

}
