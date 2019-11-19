package hangman.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hangman.game.GameAssembler;
import hangman.utils.Validator;

/**
 * This class is responsible for the main interactions with the game.
 * 
 * @author PolarBear Dev
 */
public class MainMenu {
	
	private static final Logger LOGGER = LogManager.getLogger(MainMenu.class);
	
	private static final String START = "[1] start";
	private static final String STOP = "[2] stop";
	private static final String TAAL = "[3] taal";

	private final Scanner scanner;

	public MainMenu(Scanner scanner) {
		super();
		this.scanner = scanner;
		welcomeText();
	}

	public boolean mainMenu() {
		boolean continueGame = true;
		keuzeMenuText();
		String keuze1 = "Het spel wordt nu opgestart";
		String keuze2 = "Het spel wordt nu afgesloten";
		String keuze3 = "Welke taal wil je de woorden?";
		String keuzeOnbekend = "Sorry onjuiste code ingevoerd.. Probeer opnieuw";

		String input = scanner.next();
		
		if(!isInputValid(input)) {
			mainMenu();
		}
		
		int keuze = Integer.parseInt(input);
		switch (keuze) {

		case 1:
			System.out.println(keuze1);
				
			GameAssembler assembler = new GameAssembler(scanner);
			assembler.runGameLogic();

			break;

		case 2:
			System.out.println(keuze2);
			continueGame = false;
			break;

		case 3:
			System.out.println(keuze3);
			break;
		
		default:
			System.out.println(keuzeOnbekend);
		}
		
		return continueGame;
	}

	private boolean isInputValid(String input) {
		if(!Validator.isNummeric(input)){
			System.out.println("Please enter a valid number.");
			return false;
		}
		
		if(Validator.inputIsSmallerThanSmallestValue(input, 1)){
			LOGGER.info("Input is smaller than smallest value. Input should be bigger than {}", 1);
			System.out.println("Input is smaller than smallest value.");
			return false;
		}
		
		if(Validator.inputIsGreaterThanHighestValue(input, 3)) {
			LOGGER.info("Input is greater than the higest value. Input should be smaller than {}", 3);
			System.out.println("Input is invalid. Please provide a lower number");
			return false;
		}
		return true;
		
	}

	private void welcomeText() {
		System.out.println("Welkom bij Galgje!");
	}

	private void keuzeMenuText() {
		System.out.printf("Je kan de volgende opties kiezen: %10s %10s %10s %n%n", START, STOP, TAAL);
	}
}
