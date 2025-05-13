package com.springio.demo.kubernetes.controller;

import org.springframework.web.bind.annotation.*;
import com.springio.demo.kubernetes.health.CustomLivenessHealthIndicator;
import com.springio.demo.kubernetes.health.CustomReadinessHealthIndicator;


@RestController
@RequestMapping("/simulate")
public class HealthSimulationController {

	private final CustomLivenessHealthIndicator liveness;
	private final CustomReadinessHealthIndicator readiness;

	public HealthSimulationController(CustomLivenessHealthIndicator liveness,
									  CustomReadinessHealthIndicator readiness) {
		this.liveness = liveness;
		this.readiness = readiness;
	}

	@PostMapping("/liveness/down")
	public String goLivenessDown() {
		liveness.setHealthy(false);
		return "Liveness DOWN ❌";
	}

	@PostMapping("/liveness/up")
	public String goLivenessUp() {
		liveness.setHealthy(true);
		return "Liveness UP ✅";
	}

	@PostMapping("/readiness/down")
	public String goReadinessDown() {
		readiness.setReady(false);
		return "Readiness DOWN ❌";
	}

	@PostMapping("/readiness/up")
	public String goReadinessUp() {
		readiness.setReady(true);
		return "Readiness UP ✅";
	}
}
