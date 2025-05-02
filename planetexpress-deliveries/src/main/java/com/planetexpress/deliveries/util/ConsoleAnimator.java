package com.planetexpress.deliveries.util;

import com.planetexpress.deliveries.console.AnsiColors;
import com.planetexpress.deliveries.console.AsciiArt;

public class ConsoleAnimator {

	public static void simulateDeliveryAnimation(String planet) {
		try {
			System.out.println(AnsiColors.GREEN + "\n🚀 Initiating intergalactic delivery..." + AnsiColors.RESET);
			pause();

			AsciiArt.drawSpaceship();
			Thread.sleep(1000);

			System.out.print(AnsiColors.GREEN + "🛰️  Launching from Earth");
			loadingDots();
			System.out.println(" ✅" + AnsiColors.RESET);

			System.out.print(AnsiColors.BLUE + "🌌 Navigating deep space to " + planet);
			loadingDots();
			System.out.println(" ✅" + AnsiColors.RESET);

			System.out.print(AnsiColors.YELLOW + "☄️  Dodging asteroids");
			loadingDots();
			System.out.println(" ✅" + AnsiColors.RESET);

			System.out.print(AnsiColors.CYAN + "📦 Delivering package");
			loadingDots();
			System.out.println(" ✅" + AnsiColors.RESET);

			AsciiArt.drawPlanet(planet);
			System.out.println(AnsiColors.BOLD + AnsiColors.MAGENTA + "🎉 Delivery to " + planet + " completed successfully!\n" + AnsiColors.RESET);

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println(AnsiColors.RED + "🚨 Animation interrupted." + AnsiColors.RESET);
		}
	}


	public static void simulateObstacle(String obstacle) {
		try {
			System.out.println("\n⚠️  Unexpected obstacle detected!");
			pause();

			System.out.print("🧠 Analyzing obstacle: " + obstacle);
			loadingDots();
			System.out.println(" 🧐");

			System.out.print("🔧 Recalculating route");
			loadingDots();
			System.out.println(" ✅");

			System.out.print("🔥 Boosting engines");
			loadingDots();
			System.out.println(" 🚀");

			System.out.println("💡 Obstacle '" + obstacle + "' avoided successfully!\n");

			AsciiArt.drawObstacle(obstacle);

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("🚨 Obstacle simulation interrupted.");
		}
	}


	private static void pause() throws InterruptedException {
		Thread.sleep(800);
	}

	private static void loadingDots() throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			System.out.print(".");
			Thread.sleep(500);
		}
	}
}
