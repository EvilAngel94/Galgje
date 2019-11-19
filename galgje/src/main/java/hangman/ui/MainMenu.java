package hangman.ui;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import hangman.game.GameAssembler;
import hangman.game.SelectDifficultyLevel;
import hangman.utils.Validator;

/**
 * This class is responsible for the main interactions with the game.
 * 
 * @author PolarBear Dev
 */
public class MainMenu {
	
	private static final String START = "[1] start";
	private static final String STOP = "[2] stop";
	private static final String TAAL = "[3] taal";

	private Scanner scanner;

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

		int keuze = Validator.validateUserInput(scanner, 1, 2);
		
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

	private void welcomeText() {
		System.out.println("Welkom bij Galgje!");
	}

	private void keuzeMenuText() {
		System.out.printf("Je kan de volgende opties kiezen: %10s %10s %10s %n%n", START, STOP, TAAL);
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

}
