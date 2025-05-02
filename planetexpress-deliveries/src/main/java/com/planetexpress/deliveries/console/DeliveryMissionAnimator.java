package com.planetexpress.deliveries.console;

public class DeliveryMissionAnimator {

	public static void runMission(String planet, boolean obstacleEncountered, String obstacleName) {
		try {
			System.out.println("\n🛰️  Preparing intergalactic delivery mission...\n");
			Thread.sleep(1000);

			AsciiArt.drawEarth();
			Thread.sleep(800);

			AsciiArt.drawSpaceship();
			System.out.println("🚀 Launching from Earth...");
			Thread.sleep(1200);

			System.out.println("🌌 Navigating through deep space to " + planet + "...");
			Thread.sleep(1000);
			System.out.println();

			if (obstacleEncountered) {
				System.out.println("⚠️  Obstacle detected: " + obstacleName + "!");
				Thread.sleep(800);
				AsciiArt.drawObstacle(obstacleName);
				System.out.println("🧠 Calculating alternative route...");
				Thread.sleep(1000);
				System.out.println("🔧 Obstacle bypassed successfully!\n");
			}

			AsciiArt.drawPlanet(planet);
			System.out.println("📦 Package delivered to " + planet + "! 🎉\n");
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("🚨 Mission aborted.");
		}
	}
}
