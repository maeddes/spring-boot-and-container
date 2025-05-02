package com.planetexpress.deliveries.api;

import com.planetexpress.deliveries.model.Delivery;
import com.planetexpress.deliveries.model.DeliveryStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeliveryApiIntegrationTest {

	@LocalServerPort
	int port;

	@Autowired
	TestRestTemplate rest;

	@Test
	void createAndGetDelivery_shouldWork() {
		String url = "http://localhost:" + port + "/api/deliveries?planet=Mars&contents=Space+Mail";

		ResponseEntity<Delivery> postResponse = rest.postForEntity(url, null, Delivery.class);
		assertEquals(HttpStatus.OK, postResponse.getStatusCode());

		Delivery created = postResponse.getBody();
		assertNotNull(created);
		assertEquals("Mars", created.getRecipientPlanet());

		String getUrl = "http://localhost:" + port + "/api/deliveries/" + created.getId();
		ResponseEntity<Delivery> getResponse = rest.getForEntity(getUrl, Delivery.class);
		assertEquals(HttpStatus.OK, getResponse.getStatusCode());
		assertEquals(created.getId(), getResponse.getBody().getId());
	}

	@Test
	void updateStatus_shouldChangeDeliveryStatus() {
		// Create a delivery first
		String createUrl = "http://localhost:" + port + "/api/deliveries?planet=Venus&contents=Probe";
		Delivery delivery = rest.postForEntity(createUrl, null, Delivery.class).getBody();

		// Now update its status
		String updateUrl = "http://localhost:" + port + "/api/deliveries/" + delivery.getId() + "/status?status=IN_TRANSIT";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Void> entity = new HttpEntity<>(headers);

		ResponseEntity<String> patchResponse = rest.exchange(updateUrl, HttpMethod.PATCH, entity, String.class);
		assertEquals(HttpStatus.OK, patchResponse.getStatusCode());

		// Verify the status
		Delivery updated = rest.getForEntity(
			"http://localhost:" + port + "/api/deliveries/" + delivery.getId(),
			Delivery.class
		).getBody();

		assertEquals(DeliveryStatus.IN_TRANSIT, updated.getStatus());
	}
}
