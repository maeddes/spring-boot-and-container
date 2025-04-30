package com.planetexpress.deliveries.service;

import com.planetexpress.deliveries.model.Delivery;
import com.planetexpress.deliveries.model.DeliveryStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryLogicEdgeCasesTest {

	@Test
	void deliveryToUranusWithPizza_shouldAlwaysFail() {
		DeliveryService service = new DeliveryService();
		Delivery delivery = service.createDelivery("Uranus", "Pizza");

		// Simulate obstacle — pizza to Uranus? No chance.
		service.handleObstacle(delivery.getId(), "Temperature anomaly");

		Delivery updated = service.getDelivery(delivery.getId()).get();
		if (delivery.getRecipientPlanet().equalsIgnoreCase("Uranus") &&
			delivery.getPackageContents().equalsIgnoreCase("Pizza")) {
			System.out.println("❌ Pizza cannot survive in Uranus. Delivery failed by galactic regulation.");
			assertEquals(DeliveryStatus.FAILED, updated.getStatus());
		} else {
			fail("This test only runs on pizza to Uranus!");
		}
	}
}
