package hangman.ui;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hangman.utils.Validator;
import hangman.utils.WordListReader;

/**
 * This class is responsible to select the right file to be chosen from.
 *
 */
public class SelectWordLengthUI {

	private static final Logger LOGGER = LogManager.getLogger(SelectWordLengthUI.class);

	private static final String WORDS_GALGJE_FOUR = "4_words_galgje.csv";
	private static final String WORDS_GALGJE_FIVE = "5_words_galgje.csv";

	private final Scanner scanner;

	private WordListReader uitlezen;

	public SelectWordLengthUI(Scanner scanner) {
		super();
		this.scanner = scanner;
		uitlezen = new WordListReader();
	}

	public Map<Integer, String> loadWordList() throws IOException {

		Map<Integer, String> galgjeWoorden = null;
		System.out.println("Selecteer nu je woordenLengte [1] = 4, [2] = 5 letter woorden");

		String input = scanner.next();
		if (!inputIsValid(input)) {
			loadWordList();
		}
		int keuze = Integer.parseInt(input);

		if (keuze == 1) {
			galgjeWoorden = uitlezen.readsSelectedCSVFile(WORDS_GALGJE_FOUR);
			return galgjeWoorden;
		}
		if (keuze == 2) {
			galgjeWoorden = uitlezen.readsSelectedCSVFile(WORDS_GALGJE_FIVE);
			return galgjeWoorden;
		}

		return galgjeWoorden;
	}

	private boolean inputIsValid(String input) {
		if (!Validator.isNummeric(input)) {
			System.out.println("Please enter a valid number.");
			return false;
		}

		if (Validator.inputIsSmallerThanSmallestValue(input, 1)) {
			LOGGER.info("Input is smaller than smallest value. Input should be bigger than {}", 1);
			System.out.println("Input is smaller than smallest value.");
			return false;
		}

		if (Validator.inputIsGreaterThanHighestValue(input, 2)) {
			LOGGER.info("Input is greater than the higest value. Input should be smaller than {}", 2);
			System.out.println("Input is invalid. Please provide a lower number");
			return false;
		}
		return true;

	}

}
