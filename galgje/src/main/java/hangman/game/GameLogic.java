package hangman.game;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hangman.utils.Validator;

/**
 * This class contains the Game logic
 * 
 * @author Polar Bear Development.
 *
 */
public class GameLogic {

	private static final Logger LOGGER = LogManager.getLogger(GameLogic.class);

	private Scanner scanner;
	private Map<Integer, String> galgjeWoorden;

	public GameLogic(Scanner scanner, Map<Integer, String> galgjeWoorden) {
		super();
		this.scanner = scanner;
		this.galgjeWoorden = galgjeWoorden;
	}

	/**
	 * This is the main game which will be played when choosing the game option.
	 */
	public int gamePlay(int life) {
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

	private int wantToPlayAnotherGame() {
		LOGGER.info("Do you want to play again? [1] = Yes [2] = No [3] = Different word count");
		String input = scanner.next();
		if (!isInputValid(input)) {
			return wantToPlayAnotherGame();
		}

		return Integer.parseInt(input);
	}

	private boolean isInputValid(String input) {
		if (!Validator.isNummeric(input)) {
			LOGGER.info("Please enter a valid value. Either 1 (Yes) or 2 (No).");
			return false;
		}
		if (Validator.inputIsGreaterThanHighestValue(input, 3)) {
			LOGGER.info("Please enter a number lower than 3");
			return false;
		}
		if (Validator.inputIsSmallerThanSmallestValue(input, 1)) {
			LOGGER.info("Please enter a number higher than 1");
			return false;
		}
		return true;
	}

	/**
	 * Select a random word from the HashMap.
	 * 
	 * @return the word which is selected.
	 */
	private String chooseRandomWord() {
		return galgjeWoorden.get(selectRandomKey());
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
			LOGGER.error("Hangmang word cannot be selected based on key chosen. Error: {}", e);
		}
		return 0;
	}
}
