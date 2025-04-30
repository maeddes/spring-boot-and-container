package com.planetexpress.deliveries.cli;

import com.planetexpress.deliveries.util.ConsoleAnimator;
import com.planetexpress.deliveries.console.AsciiArt;
import com.planetexpress.deliveries.model.Delivery;
import com.planetexpress.deliveries.service.DeliveryService;

import java.util.*;

public class PlanetExpressCli {

	private static final Scanner scanner = new Scanner(System.in);
	private static final DeliveryService deliveryService = new DeliveryService();

	public static void main(String[] args) {
		printWelcome();

		while (true) {
			printMenu();
			String choice = scanner.nextLine().trim();

			switch (choice) {
				case "1" -> createDelivery();
				case "2" -> simulateDelivery();
				case "3" -> simulateObstacle();
				case "4" -> exit();
				default -> System.out.println("❓ Invalid option. Try again.");
			}
		}
	}

	private static void printWelcome() {
		AsciiArt.drawSpaceship();
		System.out.println("🚀 Welcome to Planet Express CLI!");
		System.out.println("📦 Manage intergalactic deliveries from your terminal.\n");
	}

	private static void printMenu() {
		System.out.println("""
                === Main Menu ===
                1) Create new delivery
                2) Simulate delivery (animation)
                3) Simulate obstacle (manual)
                4) Exit
                Choose an option:\s""");
	}

	private static void createDelivery() {
		System.out.print("🌍 Enter destination planet: ");
		String planet = scanner.nextLine();
		System.out.print("📦 Enter package contents: ");
		String contents = scanner.nextLine();

		Delivery delivery = deliveryService.createDelivery(planet, contents);
		System.out.println("✅ Delivery created with ID: " + delivery.getId() + "\n");
	}

	private static void simulateDelivery() {
		Collection<Delivery> deliveries = deliveryService.getAllDeliveries();
		if (deliveries.isEmpty()) {
			System.out.println("⚠️ No deliveries to simulate.\n");
			return;
		}

		Delivery delivery = chooseDelivery(deliveries);
		ConsoleAnimator.simulateDeliveryAnimation(delivery.getRecipientPlanet());
	}

	private static void simulateObstacle() {
		Collection<Delivery> deliveries = deliveryService.getAllDeliveries();
		if (deliveries.isEmpty()) {
			System.out.println("⚠️ No deliveries to affect.\n");
			return;
		}

		Delivery delivery = chooseDelivery(deliveries);

		System.out.print("☄️  Enter obstacle (e.g. 'Black hole'): ");
		String obstacle = scanner.nextLine();
		deliveryService.handleObstacle(delivery.getId(), obstacle);
	}

	private static Delivery chooseDelivery(Collection<Delivery> deliveries) {
		List<Delivery> list = new ArrayList<>(deliveries);

		System.out.println("📋 Select a delivery:");
		for (int i = 0; i < list.size(); i++) {
			Delivery d = list.get(i);
			System.out.printf("%d) %s → %s [%s]%n", i + 1, d.getPackageContents(), d.getRecipientPlanet(), d.getStatus());
		}

		int index = -1;
		while (index < 1 || index > list.size()) {
			System.out.print("Choose delivery number: ");
			try {
				index = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException ignored) {
			}
		}

		return list.get(index - 1);
	}

	private static void exit() {
		System.out.println("👋 Goodbye from Planet Express!");
		System.exit(0);
	}
}
