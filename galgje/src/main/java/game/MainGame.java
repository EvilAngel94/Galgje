package game;

import java.util.Scanner;

/**
 * This class will make sure the game can be launched and played.
 * 
 * @author Polar Bear Development.
 *
 */
public class MainGame {

	private static final String START = "[1] start";
	private static final String STOP = "[2] stop";
	private static final String TAAL = "[3] taal";

	private final Scanner scanner;

	public MainGame() {
		super();
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		MainGame game = new MainGame();
		game.welcomeText();
		game.readInput();

	}

	/**
	 * Welcome text.
	 */
	private void welcomeText() {
		System.out.println("Welkom bij Galgje!");
		System.out.printf("Je kan de volgende opties kiezen: %10s %10s %10s %n%n", START, STOP, TAAL);
	}

	private void readInput() {
		String keuze1 = "Het spel wordt nu opgestart";
		String keuze2 = "Het spel wordt nu afgesloten";
		String keuze3 = "Welke taal wil je de woorden?";
		String keuzeOnbekend = "Sorry onjuiste code ingevoerd.. Probeer opnieuw";
		
		int keuze = scanner.nextInt();

		switch (keuze) {

		case 1:
			System.out.println(keuze1);
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

}
