package game;

import java.util.Scanner;

public class SelectDifficultyLevel {

	private Scanner scanner;
	
	public SelectDifficultyLevel(Scanner scanner) {
		super();
		this.scanner = scanner;
		System.out.println("Geef aan hoeveel levens je wilt hebben, mag niet meer dan 10 zijn.");
	}

	public int selectAmountOfLives() {
		int difficulty = 0;
		boolean validate = false;

		do {
			if (scanner.hasNextInt()) { // Checks if the input is a valid integer
				int levens = scanner.nextInt();
				if (levens <= 0) {
					System.out.println("Input moet groter dan 0 zijn");
					return selectAmountOfLives();
				}
				if (levens > 10) {
					System.out.println("Input mag niet groter zijn dan 10");
					return selectAmountOfLives();
				}
				validate = true;
				return difficulty = levens;
			} else {
				System.out.println("Verkeerde input, kan alleen hele getallen zijn");
				scanner.next();
			}
		} while (validate == false);
		System.out.println("Je hebt gekozen om met " + difficulty + " levens te spelen.");
		return difficulty;

	}
}
