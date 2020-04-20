package hangman.ui;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hangman.game.wordlist.WordlistInteractions;
import hangman.utils.PropertyReader;
import hangman.utils.Validator;

/**
 * This class is responsible for the interactions when the player has the choice
 * to choose the lenght of the words to be played with.
 * 
 * @author PolarBear Dev
 *
 */
public class SelectWordLengthUI {

	private static final Logger LOGGER = LogManager.getLogger(SelectWordLengthUI.class);

	private static final String DUTCH_WORD_LIST = "dutch.csv";
	private static final String ENGLISH_WORD_LIST = "english.csv";

	private final Scanner scanner;
	private boolean isDutch;

	public SelectWordLengthUI(Scanner scanner, boolean isDutch) {
		super();
		this.scanner = scanner;
		this.isDutch = isDutch;
	}

	public Map<Integer, String> loadWordList() throws IOException {
		PropertyReader.getInstance();

		Map<Integer, String> galgjeWoorden = null;
		System.out.println(PropertyReader.getProperty("select.word.choices", isDutch));

		String input = scanner.next();
		if (!inputIsValid(input)) {
			loadWordList();
		}
		int keuze = Integer.parseInt(input);

		if (keuze == 1) {
			String woordenlijst = "4_words_" + (isDutch ? DUTCH_WORD_LIST : ENGLISH_WORD_LIST);
			galgjeWoorden = WordlistInteractions.readDefaultWordlist(woordenlijst);
			return galgjeWoorden;
		}
		if (keuze == 2) {
			String woordenlijst = "5_words_" + (isDutch ? DUTCH_WORD_LIST : ENGLISH_WORD_LIST);
			galgjeWoorden = WordlistInteractions.readDefaultWordlist(woordenlijst);
			return galgjeWoorden;
		}

		return galgjeWoorden;
	}

	private boolean inputIsValid(String input) {
		if (!Validator.isNummeric(input)) {
			System.out.println(PropertyReader.getProperty("validation.input.invalid", isDutch));
			return false;
		}

		if (Validator.inputIsSmallerThanSmallestValue(input, 1)) {
			LOGGER.debug("Input is smaller than smallest value. Input should be bigger than {}", 1);
			System.out.println(PropertyReader.getProperty("validation.input.toosmall", isDutch));
			return false;
		}

		if (Validator.inputIsGreaterThanHighestValue(input, 2)) {
			LOGGER.debug("Input is greater than the higest value. Input should be smaller than {}", 2);
			System.out.println(PropertyReader.getProperty("validation.input.toobig", isDutch));
			return false;
		}
		return true;
	}

}
