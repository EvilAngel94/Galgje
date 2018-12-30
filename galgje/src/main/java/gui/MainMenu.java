package gui;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is responsible for the main menu of the game. All the calls to other GUI's are made from here and also received if this is necessary.
 * 
 * @author PolarBear Dev
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
	}

	public void mainMenu(Scanner scanner) {
		welcomeText();
		String keuze1 = "Het spel wordt nu opgestart";
		String keuze2 = "Het spel wordt nu afgesloten";
		String keuze3 = "Welke taal wil je de woorden?";
		String keuzeOnbekend = "Sorry onjuiste code ingevoerd.. Probeer opnieuw";

		int keuze = scanner.nextInt();

		switch (keuze) {

		case 1:
			System.out.println(keuze1);
			SelectWordLengthGUI selectLengthGUI = new SelectWordLengthGUI(scanner);
			try {
				galgjeWoorden = selectLengthGUI.loadWordList();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			GameGUI game = new GameGUI(scanner, galgjeWoorden);
			game.gamePlay();

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

	/**
	 * Welcome text.
	 */
	private void welcomeText() {
		System.out.println("Welkom bij Galgje!");
		System.out.printf("Je kan de volgende opties kiezen: %10s %10s %10s %n%n", START, STOP, TAAL);
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

}
