package hangman.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hangman.game.GameAssembler;
import hangman.utils.PropertyReader;
import hangman.utils.Validator;

/**
 * This class is responsible for the main interactions with the game.
 * 
 * @author PolarBear Dev
 */
public class MainMenu {

	private static final Logger LOGGER = LogManager.getLogger(MainMenu.class);

	private final Scanner scanner;
	private boolean dutch = true;

	public MainMenu(Scanner scanner) {
		super();
		this.scanner = scanner;
		PropertyReader.getInstance();
		System.out.println(PropertyReader.getProperty("welcome.message", isDutch()));
	}

	public boolean mainMenu() {
		boolean continueGame = true;
		System.out.println(PropertyReader.getProperty("mainmenu.menu.choices", isDutch()));

		String userInput = scanner.next();

		if (!isInputValid(userInput)) {
			mainMenu();
		}
		int keuze = Integer.parseInt(userInput);
		switch (keuze) {

		case 1: // Main gameplay
			System.out.println(PropertyReader.getProperty("mainmenu.choice.one", isDutch()));

			GameAssembler assembler = new GameAssembler(scanner);
			assembler.runGameLogic();

			break;

		case 2: // Stop the game
			System.out.println(PropertyReader.getProperty("mainmenu.choice.two", isDutch()));
			continueGame = false;
			break;

		case 3: // Select a different language
			System.out.println(PropertyReader.getProperty("mainmenu.choice.three", isDutch()));
			break;

		default: // Unknow choice
			System.out.println(PropertyReader.getProperty("mainmenu.choice.unknow", isDutch()));
		}

		return continueGame;
	}

	private boolean isInputValid(String input) {
		if (!Validator.isNummeric(input)) {
			System.out.println(PropertyReader.getProperty("validation.input.invalid", isDutch()));
			return false;
		}

		if (Validator.inputIsSmallerThanSmallestValue(input, 1)) {
			LOGGER.debug("Input is smaller than smallest value. Input should be bigger than {}", 1);
			System.out.println(PropertyReader.getProperty("validation.input.toosmall", isDutch()));
			return false;
		}

		if (Validator.inputIsGreaterThanHighestValue(input, 3)) {
			LOGGER.debug("Input is greater than the higest value. Input should be smaller than {}", 3);
			System.out.println(PropertyReader.getProperty("validation.input.toobig", isDutch()));
			return false;
		}
		return true;

	}

	public boolean isDutch() {
		return dutch;
	}

}
