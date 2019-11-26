package hangman.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hangman.utils.PropertyReader;
import hangman.utils.Validator;

public class SelectDifficultyLevelUI {
	private static final Logger LOGGER = LogManager.getLogger(SelectDifficultyLevelUI.class);

	private final Scanner scanner;

	private boolean isDutch;

	public SelectDifficultyLevelUI(Scanner scanner, boolean isDutch) {
		super();
		this.scanner = scanner;
		this.isDutch = isDutch;

		PropertyReader.getInstance();
	}

	public int selectAmountOfLives() {
		System.out.println(PropertyReader.getProperty("select.difficulty", isDutch));

		String input = scanner.next();
		if (!inputIsValid(input)) {
			selectAmountOfLives();
		}

		return Integer.parseInt(input);
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

		if (Validator.inputIsGreaterThanHighestValue(input, 10)) {
			LOGGER.debug("Input is greater than the higest value. Input should be smaller than {}", 10);
			System.out.println(PropertyReader.getProperty("validation.input.toobig", isDutch));
			return false;
		}
		return true;
	}
}
