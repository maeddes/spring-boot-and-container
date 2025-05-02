package com.planetexpress.deliveries.console;

import java.awt.*;

public class StartupAnimation {

	public static void show() {
		try {
			System.out.println("\n🧠 Booting up Planet Express delivery system...\n");
			Thread.sleep(800);

			AsciiArt.drawSpaceship();
			Thread.sleep(1000);

			System.out.println("🔋 Charging warp core...");
			loading();
			System.out.println("✅ Warp core online!\n");

			System.out.println("📡 Connecting to planetary network...");
			loading();
			System.out.println("✅ All planets reachable!\n");

			System.out.println("🚀 Planet Express ready for galactic operations!\n");

			// 🎵 Beep!
			Toolkit.getDefaultToolkit().beep();

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("⚠️ Startup animation interrupted.");
		} catch (HeadlessException e) {
			System.out.println("🛑 No GUI available: cannot beep.");
		}
	}

	private static void loading() throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			System.out.print(".");
			Thread.sleep(500);
		}
		System.out.println();
	}
}
