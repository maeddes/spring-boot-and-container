package com.planetexpress.deliveries.service;

import com.planetexpress.deliveries.model.Delivery;
import com.planetexpress.deliveries.model.DeliveryStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryServiceTests {

	@Test
	void createDelivery_shouldHavePendingStatus() {
		DeliveryService service = new DeliveryService();
		Delivery delivery = service.createDelivery("Venus", "Chocolate");

		assertNotNull(delivery.getId());
		assertEquals("Venus", delivery.getRecipientPlanet());
		assertEquals(DeliveryStatus.PENDING, delivery.getStatus());
	}

	@Test
	void updateStatus_shouldWorkCorrectly() {
		DeliveryService service = new DeliveryService();
		Delivery delivery = service.createDelivery("Jupiter", "Rock samples");

		boolean updated = service.updateStatus(delivery.getId(), DeliveryStatus.IN_TRANSIT);

		assertTrue(updated);
		assertEquals(DeliveryStatus.IN_TRANSIT, service.getDelivery(delivery.getId()).get().getStatus());
	}

	@Test
	void handleObstacle_shouldChangeStatusToEitherInTransitOrFailed() {
		DeliveryService service = new DeliveryService();
		Delivery delivery = service.createDelivery("Saturn", "Band merch");

		service.handleObstacle(delivery.getId(), "Meteor shower");

		Delivery updated = service.getDelivery(delivery.getId()).get();
		assertTrue(updated.getStatus() == DeliveryStatus.IN_TRANSIT || updated.getStatus() == DeliveryStatus.FAILED);
	}
}
