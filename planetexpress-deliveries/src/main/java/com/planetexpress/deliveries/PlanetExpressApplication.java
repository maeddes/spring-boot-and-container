package com.planetexpress.deliveries;

import com.planetexpress.deliveries.console.StartupAnimation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlanetExpressApplication {

	public static void main(String[] args) {
		StartupAnimation.show(); // 🚀 Animation
		SpringApplication.run(PlanetExpressApplication.class, args);
	}
}
