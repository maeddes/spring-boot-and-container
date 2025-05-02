package com.planetexpress.deliveries;

import org.springframework.boot.SpringApplication;

public class TestDeliveriesApplication {

	public static void main(String[] args) {
		SpringApplication.from(PlanetExpressApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
