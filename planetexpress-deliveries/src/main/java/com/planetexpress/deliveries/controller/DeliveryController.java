package com.planetexpress.deliveries.controller;

import com.planetexpress.deliveries.model.Delivery;
import com.planetexpress.deliveries.model.DeliveryStatus;
import com.planetexpress.deliveries.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

	private final DeliveryService deliveryService;

	public DeliveryController(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}

	@PostMapping
	public ResponseEntity<Delivery> createDelivery(@RequestParam String planet,
												   @RequestParam String contents) {
		Delivery delivery = deliveryService.createDelivery(planet, contents);
		return ResponseEntity.ok(delivery);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Delivery> getDelivery(@PathVariable UUID id) {
		return deliveryService.getDelivery(id)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@PatchMapping("/{id}/status")
	public ResponseEntity<String> updateStatus(@PathVariable UUID id,
											   @RequestParam DeliveryStatus status) {
		boolean updated = deliveryService.updateStatus(id, status);
		return updated ? ResponseEntity.ok("Status updated.")
			: ResponseEntity.notFound().build();
	}

	@GetMapping
	public ResponseEntity<Collection<Delivery>> listAll() {
		return ResponseEntity.ok(deliveryService.getAllDeliveries());
	}

	@PatchMapping("/{id}/obstacle")
	public ResponseEntity<String> handleObstacle(@PathVariable UUID id,
												 @RequestParam String obstacle) {
		Optional<Delivery> deliveryOpt = deliveryService.getDelivery(id);
		if (deliveryOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		deliveryService.handleObstacle(id, obstacle);
		Delivery updated = deliveryService.getDelivery(id).get();
		return ResponseEntity.ok("Obstacle handled. Current status: " + updated.getStatus());
	}


}
