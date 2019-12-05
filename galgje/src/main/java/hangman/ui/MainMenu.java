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
	private boolean isDutch;

	public MainMenu(Scanner scanner) {
		super();
		this.scanner = scanner;
		this.isDutch = true;
		
		PropertyReader.getInstance();
	}

	public boolean mainMenu() {
		boolean continueGame = true;
		System.out.println(PropertyReader.getProperty("mainmenu.menu.choices", isDutch));

		String userInput = scanner.next();

		if (!isInputValid(userInput)) {
			mainMenu();
		}
		
		int keuze = Integer.parseInt(userInput);
	
		switch (keuze) {
		case 1: // Main gameplay
			System.out.println(PropertyReader.getProperty("mainmenu.choice.one", isDutch));

			GameAssembler assembler = new GameAssembler(scanner, isDutch);
			assembler.runGameLoop();
			break;

		case 2: // Stop the game
			System.out.println(PropertyReader.getProperty("mainmenu.choice.two", isDutch));
			continueGame = false;
			break;

		case 3: // Select a different language
			System.out.println(PropertyReader.getProperty("mainmenu.choice.three", isDutch));

			userInput = scanner.next();

			if (!isInputValid(userInput)) {
				mainMenu();
			}

			selectLanguage(Integer.parseInt(userInput));
			break;

		default: // Unknow choice
			System.out.println(PropertyReader.getProperty("mainmenu.choice.unknow", isDutch));
		}

		return continueGame;
	}

	private void selectLanguage(int userInput) {
		if (userInput == 1) {
			setIsDutch(true);
			System.out.println(PropertyReader.getProperty("mainmenu.choice.three.changed", isDutch));
		}
		if (userInput == 2) {
			setIsDutch(false);
			System.out.println(PropertyReader.getProperty("mainmenu.choice.three.changed", isDutch));
		}
		if (userInput < 1 || userInput > 2) {
			System.out.println(PropertyReader.getProperty("validation.input.invalid", isDutch));
		}
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

	/*
	 * This setter is only used to set a different language. 
	 * At this moment, only Dutch or English can be played.
	 */
	private void setIsDutch(boolean isDutch) {
		this.isDutch = isDutch;
	}

}
