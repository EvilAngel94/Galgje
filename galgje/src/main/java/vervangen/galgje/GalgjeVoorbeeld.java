package vervangen.galgje;

import java.util.ArrayList;
import java.util.Scanner;

public class GalgjeVoorbeeld {

	private final Scanner scanner;

	private String[] woorden = { "Baan", "apen", "onzin", "test" };

	public GalgjeVoorbeeld(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	public void uitvoeren() {
		int life = 5;
		int numberOfGuesses = 0;
		String woord = woorden[1];
		

		char[] filler = new char[woord.length()];
		int i = 0;
		while (i < woord.length()) {
			filler[i] = '-';
			if (woord.charAt(i) == ' ') {
				filler[i] = ' ';
			}
			i++;
		}

		System.out.print(filler);
		System.out.println("    life remaining = " + life);
		ArrayList<Character> list = new ArrayList<Character>();
		
		while (life > 0) { // gaat door zolang het kan.
			char x = scanner.next().charAt(0);

			if (list.contains(x)) {
				System.out.println("You've already enterd: " + x);
				continue;
			}
			
			list.add(x);
			if (woord.contains(x + "")) {
				for (int y = 0; y < woord.length(); y++) {
					if (woord.charAt(y) == x) { // checks the character and will replace '-' by the character.
						filler[y] = x;
					}
				}
			} else {
				life--; // Gaat leven eraf
			}
			if (woord.equals(String.valueOf(filler))) {
				System.out.println(filler);
				System.out.println("You won!");
				break;
			}

			System.out.print(filler);
			System.out.println("    life remaining = " + life);
		}

		if (life == 0) {
			System.out.println("You lost");
		}
	}

}
