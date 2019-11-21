package hangman.launcher;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hangman.ui.MainMenu;
import hangman.utils.PropertyReader;

public class MainApplication {
	
	private static final Logger LOGGER = LogManager.getLogger(MainApplication.class);

	private final Scanner scanner;
	
	public MainApplication() {
		super();
		this.scanner = new Scanner(System.in);
		PropertyReader.getInstance();
		System.out.println(PropertyReader.getProperty("welcome.message", true));
	}
	
	public static void main(String[] args) {
		new MainApplication().gameLoop();
	}
	
	private void gameLoop() {
		LOGGER.debug("Game is starting..");
		boolean gameLoop;
		
		MainMenu mainMenu = new MainMenu(scanner);
		
		do {
			gameLoop = mainMenu.mainMenu();
			
		} while (gameLoop);
		
		LOGGER.debug("Game is finished. The application is closable now.");
	}
}