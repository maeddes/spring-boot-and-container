package com.planetexpress.deliveries.console;

public class AsciiArt {

	public static void drawSpaceship() {
		System.out.println(AnsiColors.GREEN + """
         .
         |
        / \\
       / _ \\
      |.o '.|
      |'._.'|
      |     |
     ,'|  |  |`.
    /  |  |  |  \\
    |,-'--|--'-.|
     🚀 PLANET EXPRESS SHIP
    """ + AnsiColors.RESET);
	}


	public static void drawEarth() {
		System.out.println("""
              🌍 EARTH
               _____
            .-'     '-.
          .'           '.
         /               \\
        |                 |
         \\               /
          '.           .'
            '-.___.-'
        """);
	}

	public static void drawPlanet(String name) {
		System.out.println(AnsiColors.BLUE + """
               🪐 """ + name.toUpperCase() + """
               _____
            .-'     '-.
          .'           '.
         /               \\
        |                 |
         \\               /
          '.           .'
            '-.___.-'
        """);
	}

	public static void drawObstacle(String name) {
		System.out.println(AnsiColors.RED + """
        ☄️ OBSTACLE: """ + name.toUpperCase() + """
        ~~~~~~~~~~~~~~
       *   *    *   *
     *    ☄️    *    *
       *    *    *   *
        ~~~~~~~~~~~~~~
    """ + AnsiColors.RESET);
	}

}
