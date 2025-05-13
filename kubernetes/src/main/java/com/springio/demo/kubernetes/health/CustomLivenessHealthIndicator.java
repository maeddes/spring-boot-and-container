package com.springio.demo.kubernetes.health;

import org.springframework.boot.actuate.health.*;
import org.springframework.stereotype.Component;

@Component("customLivenessIndicator")
public class CustomLivenessHealthIndicator implements HealthIndicator {

	private boolean healthy = true;

	@Override
	public Health health() {
		return healthy
			? Health.up().withDetail("liveness", "All good ✅").build()
			: Health.down().withDetail("liveness", "Simulated failure ❌").build();
	}

	public void setHealthy(boolean healthy) {
		this.healthy = healthy;
	}
}
