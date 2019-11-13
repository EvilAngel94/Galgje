package launcher;

import java.util.Scanner;

import ui.MainMenu;

public class MainApplication {

	private final Scanner scanner;
	
	public MainApplication() {
		super();
		this.scanner = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new MainApplication().gameLoop();
	}
	
	private void gameLoop() {
		boolean gameLoop;
		
		do {
			gameLoop = new MainMenu(scanner).mainMenu();
			
		} while (gameLoop);
		
		System.out.println("Game is finished. You can close the application now.");
	}
}