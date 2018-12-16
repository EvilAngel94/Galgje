package game;

import java.util.Scanner;

import gui.MainMenu;

/**
 * This class will make sure the game can be launched and played.
 * 
 * @author Polar Bear Development.
 *
 */
public class MainGame {

	private final Scanner scanner;
	private boolean gameLoop = true;

	public MainGame() {
		super();
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		MainGame game = new MainGame();
		game.gameLoop();

	}

	private void gameLoop() {
		MainMenu menu = new MainMenu(scanner);
		
		while(gameLoop) {
			menu.mainMenu(scanner);
			
		}
		
		
	}

}
