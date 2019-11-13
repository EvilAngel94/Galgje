package game;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * This class contains the Game logic
 * 
 * @author Polar Bear Development.
 *
 */
public class GameLogic {

	private Scanner scanner;
	private Map<Integer, String> galgjeWoorden;
	private int difficulty;

	public GameLogic(Scanner scanner, Map<Integer, String> galgjeWoorden, int difficulty) {
		super();
		this.scanner = scanner;
		this.galgjeWoorden = galgjeWoorden;
		this.difficulty = difficulty;
	}

	/**
	 * This is the main game which will be played when choosing the game option.
	 */
	public boolean gamePlay() {
		int life = difficulty;
		// int numberOfGuesses = 0;
		String woord = chooseRandomWord();

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
		ArrayList<Character> list = new ArrayList<>();

		while (life > 0) { // gaat door zolang het kan.
			char x = scanner.next().charAt(0); // Controle puur op eerste imput

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
			System.out.println(woord);
			System.out.println("You lost");
		}

		return wantToPlayAnotherGame();
	}

	//TODO: validator toevoegen
	private boolean wantToPlayAnotherGame() {
		System.out.println("Do you want to play again? [1] = Yes [2] = No");
		int waarde = scanner.nextInt();

		if (waarde < 0 || waarde > 2) {
			System.out.println("Please enter a valid value. Either 1 (Yes) or 2 (No).");
			return wantToPlayAnotherGame();
		}
		if (waarde == 1) {
			return true;
		}
		if (waarde == 2) {
			return false;
		}
		return wantToPlayAnotherGame();
	}

	/**
	 * Select a random word from the HashMap.
	 * 
	 * @return the word which is selected.
	 */
	private String chooseRandomWord() {
		int keyValue = selectRandomKey();
		if (keyValue == 0) {
			System.out.println("ERRORRR! Geen geldige waarde gevonden!");
		}
		return galgjeWoorden.get(keyValue);

	}

	/**
	 * This method generates a random number. The number which is randomly generated
	 * is used to pick the corresponding word.
	 * 
	 * @return keyValue
	 */
	private int selectRandomKey() {
		Random random;
		try {
			random = SecureRandom.getInstanceStrong();
			int maxValue = galgjeWoorden.size();
			return random.nextInt((maxValue + 1 - 1) + 1) + 1;

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
