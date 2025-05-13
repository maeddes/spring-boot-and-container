package com.springio.demo.kubernetes.health;

import org.springframework.boot.actuate.health.*;
import org.springframework.stereotype.Component;

@Component("customReadinessIndicator")
public class CustomReadinessHealthIndicator implements HealthIndicator {

	private boolean ready = true;

	@Override
	public Health health() {
		return ready
			? Health.up().withDetail("readiness", "Ready to serve traffic ✅").build()
			: Health.down().withDetail("readiness", "Not ready ❌").build();
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}
}
