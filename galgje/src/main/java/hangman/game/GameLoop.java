package hangman.game;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hangman.game.save.GameSaveInteractions;
import hangman.game.save.UserGameData;
import hangman.utils.PropertyReader;
import hangman.utils.Validator;

/**
 * This class is responsible for the main game loop.
 * 
 * @author Polar Bear Development.
 *
 */
public class GameLoop {

	private static final Logger LOGGER = LogManager.getLogger(GameLoop.class);

	private Scanner scanner;
	private Map<Integer, String> galgjeWoorden;
	private boolean isDutch;

	public GameLoop(Scanner scanner, Map<Integer, String> galgjeWoorden, boolean isDutch) {
		super();
		this.scanner = scanner;
		this.galgjeWoorden = galgjeWoorden;
		this.isDutch = isDutch;

		PropertyReader.getInstance();
	}

	/**
	 * This is the main game which will be played when choosing the game option.
	 */
	public int mainGameLoop(int life) {
		String word = chooseRandomWord();
		int startLives = life;

		char[] filler = createEmptyPlayableLine(word);

		ArrayList<Character> alreadyChosenOptions = new ArrayList<>();

		// While the player has lives left, the main gameloop will run.
		while (life > 0) {
			System.out.println(
					String.valueOf(filler) + " " + PropertyReader.getProperty("game.loop.life", isDutch) + life);
			char inputOfUser = scanner.next().charAt(0);

			if (alreadyChosenOptions.contains(inputOfUser)) {
				System.out.println(PropertyReader.getProperty("game.loop.alreadychosen", isDutch) + " " + inputOfUser);
				continue;
			}

			alreadyChosenOptions.add(inputOfUser);
			life = checkIfWordContainsInputCharacter(life, word, filler, inputOfUser);

			if (word.equals(String.valueOf(filler))) {
				System.out.println(String.valueOf(filler) + PropertyReader.getProperty("game.loop.win", isDutch));
				return wantToPlayAnotherGame(true, startLives - life);
			}
		}

		if (life == 0) {
			System.out.println(PropertyReader.getProperty("game.loop.correctword", isDutch) + word
					+ PropertyReader.getProperty("game.loop.lost", isDutch));
		}

		return wantToPlayAnotherGame(false, startLives);
	}

	private int checkIfWordContainsInputCharacter(int life, String woord, char[] filler, char inputOfUser) {

		if (woord.contains(inputOfUser + "")) {
			for (int letterInWord = 0; letterInWord < woord.length(); letterInWord++) {
				// checks the character and will replace '-' by the character.
				if (woord.charAt(letterInWord) == inputOfUser) {
					filler[letterInWord] = inputOfUser;
				}
			}
		} else {
			life--; // Gaat leven eraf
		}
		return life;
	}

	/*
	 * Creates the first representation of the word. Meaning, the length of the word
	 * will be converted to '-' characters.
	 */
	private char[] createEmptyPlayableLine(String woord) {
		char[] filler = new char[woord.length()];
		int index = 0;
		while (index < woord.length()) {
			filler[index] = '-';
			// When a space is present in the word, it will be displayed as space. Not as
			// '-'.
			if (woord.charAt(index) == ' ') {
				filler[index] = ' ';
			}
			index++;
		}

		return filler;
	}

	private int wantToPlayAnotherGame(boolean wordSolved, int lives) {
		System.out.println(PropertyReader.getProperty("game.another", isDutch));
		String input = scanner.next();

		if (!isInputValid(input)) {
			return wantToPlayAnotherGame(wordSolved, lives);
		}
		saveUserStats(wordSolved, lives);
		return Integer.parseInt(input);
	}

	/*
	 * This method is responsible for saving the stats of the game.
	 * 
	 * @param gamePlayed is always 1,
	 * 
	 * @param wordSolved depends if the word is guessed correctly or not
	 * 
	 * @lives is the start lives the player choose to play with.
	 */
	private void saveUserStats(boolean wordSolved, int lives) {
		int solved = wordSolved ? 1 : 0;
		GameSaveInteractions.saveUserGameData(new UserGameData(solved, lives));
	}

	/*
	 * Select a random word from the HashMap.
	 * 
	 * @return the word which is selected.
	 */
	private String chooseRandomWord() {
		return galgjeWoorden.get(selectRandomKey());
	}

	/*
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

	private boolean isInputValid(String input) {
		if (!Validator.isNummeric(input)) {
			System.out.println(PropertyReader.getProperty("validation.input.invalid", isDutch));
			return false;
		}

		if (Validator.inputIsSmallerThanSmallestValue(input, 1)) {
			LOGGER.debug("Input is smaller than smallest value. Input should be bigger than {}", 1);
			System.out.println(PropertyReader.getProperty("validation.input.toosmall", isDutch));
			return false;
		}

		if (Validator.inputIsGreaterThanHighestValue(input, 3)) {
			LOGGER.debug("Input is greater than the higest value. Input should be smaller than {}", 3);
			System.out.println(PropertyReader.getProperty("validation.input.toobig", isDutch));
			return false;
		}

		return true;
	}
}
