package ui;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import game.GameAssembler;
import game.SelectDifficultyLevel;

/**
 * This class is responsible for the main menu of the game. All the calls to
 * other GUI's are made from here and also received if this is necessary.
 * 
 * @author PolarBear Dev
 * 
 *         https://wiki.jmonkeyengine.org/jme3/advanced/nifty_gui.html
 *
 */
public class MainMenu {

	// TODO: deze moeten nog weg.
	private static final String START = "[1] start";
	private static final String STOP = "[2] stop";
	private static final String TAAL = "[3] taal";

	private Scanner scanner;
	private Map<Integer, String> galgjeWoorden;

	public MainMenu(Scanner scanner) {
		super();
		this.setScanner(scanner);
		welcomeText();
	}

	public void mainMenu(Scanner scanner) {
		keuzeMenuText();
		String keuze1 = "Het spel wordt nu opgestart";
		String keuze2 = "Het spel wordt nu afgesloten";
		String keuze3 = "Welke taal wil je de woorden?";
		String keuzeOnbekend = "Sorry onjuiste code ingevoerd.. Probeer opnieuw";

		int keuze = validationInput(scanner);

		switch (keuze) {

		case 1:
			System.out.println(keuze1);
			SelectWordLengthUI selectLengthGUI = new SelectWordLengthUI(scanner);
			try {
				galgjeWoorden = selectLengthGUI.loadWordList();

			} catch (IOException e) {
				e.printStackTrace();
			}
			SelectDifficultyLevel difficultyLevel = new SelectDifficultyLevel(scanner);
			int difficulty = difficultyLevel.selectAmountOfLives();
			GameAssembler assembler = new GameAssembler(scanner, galgjeWoorden, difficulty);
			assembler.runGameLogic();

			break;

		case 2:
			System.out.println(keuze2);
			break;

		case 3:
			System.out.println(keuze3);
			break;

		default:
			System.out.println(keuzeOnbekend);
		}

	}

	private int validationInput(Scanner scanner) {
		int keuze = 0;
		boolean validate = false;

		do {
			if (scanner.hasNextInt()) { // Checks if the input is a valid integer
				keuze = scanner.nextInt();
				if (keuze <= 0) {
					System.out.println("Input moet groter dan 0 zijn");
					return validationInput(scanner);
				}
				if (keuze > 3) {
					System.out.println("Input mag niet groter zijn dan 3");
					return validationInput(scanner);
				}
				validate = true;
				return keuze;
			} else {
				System.out.println("Verkeerde input, kan alleen hele getallen zijn");
				scanner.next();
			}
		} while (validate == false);

		return keuze;
	}

	/**
	 * Welcome text.
	 */
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
