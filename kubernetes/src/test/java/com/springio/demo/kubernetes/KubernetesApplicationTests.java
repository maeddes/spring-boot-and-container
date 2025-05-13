package com.springio.demo.kubernetes;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class KubernetesApplicationTests {

	@Test
	void contextLoads() {
	}

}
