package ui;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import game.GameAssembler;
import game.SelectDifficultyLevel;
import utils.Validate;

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

	//This is the window that will be the UI
	private long window;
	
	// TODO: deze moeten nog weg.
	private static final String START = "[1] start";
	private static final String STOP = "[2] stop";
	private static final String TAAL = "[3] taal";

	private Scanner scanner;
	private Map<Integer, String> galgjeWoorden;

	private Validate validate;

	public MainMenu(Scanner scanner) {
		super();
		this.setScanner(scanner);
		validate = new Validate(getScanner());
		welcomeText();
	}

	public void mainMenu(Scanner scanner) {
		keuzeMenuText();
		String keuze1 = "Het spel wordt nu opgestart";
		String keuze2 = "Het spel wordt nu afgesloten";
		String keuze3 = "Welke taal wil je de woorden?";
		String keuzeOnbekend = "Sorry onjuiste code ingevoerd.. Probeer opnieuw";

		int keuze = validate.valideerKeuzeMainMenu();

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
