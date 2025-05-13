package com.springio.demo.kubernetes;

import org.springframework.boot.SpringApplication;

public class TestKubernetesApplication {

	public static void main(String[] args) {
		SpringApplication.from(KubernetesApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
