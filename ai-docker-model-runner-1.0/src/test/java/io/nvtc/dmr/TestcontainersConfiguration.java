package io.nvtc.dmr;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.DockerModelRunnerContainer;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    DockerModelRunnerContainer socat() {
        // Proxy Container  
        return new DockerModelRunnerContainer("alpine/socat:1.8.0.3");
    }
    
    @Bean
    DynamicPropertyRegistrar properties(DockerModelRunnerContainer dockerModelRunnerContainer) {
        return (registrar) -> {
            registrar.add("spring.ai.openai.base-url", dockerModelRunnerContainer::getOpenAIEndpoint);
            registrar.add("spring.ai.openai.api-key", () -> "my-api-key");
            registrar.add("spring.ai.openai.chat.options.model", () -> "ai/gemma3");
        };
    }


}