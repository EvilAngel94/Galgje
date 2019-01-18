package launcher;

import java.util.Scanner;

import ui.MainMenu;

public class Launcher {

	private final Scanner scanner;
	private boolean gameLoop = true;
	
	public Launcher() {
		super();
		this.scanner = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		Launcher launcher = new Launcher();
		launcher.gameLoop();

	}
	
	private void gameLoop() {
		MainMenu menu = new MainMenu(scanner);
		
		while(gameLoop) {
			menu.mainMenu(scanner);
		}	
	}
}