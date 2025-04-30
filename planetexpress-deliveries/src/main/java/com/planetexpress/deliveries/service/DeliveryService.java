package com.planetexpress.deliveries.service;

import com.planetexpress.deliveries.console.AnsiColors;
import com.planetexpress.deliveries.console.AsciiArt;
import com.planetexpress.deliveries.model.Delivery;
import com.planetexpress.deliveries.model.DeliveryStatus;
import com.planetexpress.deliveries.util.ConsoleAnimator;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeliveryService {

	private final Map<UUID, Delivery> deliveries = new HashMap<>();

	public Delivery createDelivery(String planet, String contents) {
		Delivery delivery = new Delivery(planet, contents);
		deliveries.put(delivery.getId(), delivery);

		// Galactic delivery animation!
		ConsoleAnimator.simulateDeliveryAnimation(planet);

		return delivery;
	}

	public boolean handleObstacle(UUID id, String obstacle) {
		Delivery delivery = deliveries.get(id);
		if (delivery == null) return false;

		// Animate the obstacle
		ConsoleAnimator.simulateObstacle(obstacle);

		// 👽 Special case: Pizza cannot be delivered to Uranus!
		if ("uranus".equalsIgnoreCase(delivery.getRecipientPlanet()) &&
			"pizza".equalsIgnoreCase(delivery.getPackageContents())) {

			AsciiArt.drawPlanet("URANUS");
			System.out.println(AnsiColors.RED + AnsiColors.BOLD + "💀 Galactic Health Authority BLOCKED the delivery!" + AnsiColors.RESET);
			System.out.println(AnsiColors.RED + "❌ Pizza cannot survive in Uranus. Delivery failed by galactic regulation.\n" + AnsiColors.RESET);
			System.out.println(AnsiColors.RED + AnsiColors.BOLD + "🚫 MISSION FAILED" + AnsiColors.RESET);

			delivery.setStatus(DeliveryStatus.FAILED);
			return true;
		}


		// Regular logic: 30% chance of failure
		boolean failed = Math.random() < 0.3;
		delivery.setStatus(failed ? DeliveryStatus.FAILED : DeliveryStatus.IN_TRANSIT);

		return true;
	}

	public Optional<Delivery> getDelivery(UUID id) {
		return Optional.ofNullable(deliveries.get(id));
	}

	public boolean updateStatus(UUID id, DeliveryStatus status) {
		Delivery delivery = deliveries.get(id);
		if (delivery != null) {
			delivery.setStatus(status);
			return true;
		}
		return false;
	}

	public Collection<Delivery> getAllDeliveries() {
		return deliveries.values();
	}
}
