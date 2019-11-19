package hangman.game;

import java.util.Map;
import java.util.Scanner;

/**
 * This class is responsible to combine all the information gathered for the startup of the game.
 * As well as processing the incoming data and distributing this to the other components.
 * 
 * {@link GameLogic} 
 *  
 * @author PolarBear Dev
 *
 */
public class GameAssembler {

	private Scanner scanner;
	private Map<Integer, String> galgjeWoorden;
	
	public GameAssembler(Scanner scanner, Map<Integer, String> galgjeWoorden) {
		super();
		this.scanner = scanner;
		this.galgjeWoorden = galgjeWoorden;
	}

	/**
	 * This method is responsible for running the game itself.
	 */
	public void runGameLogic() {
		boolean returnToMainMenu;
		
		GameLogic gameLogic = new GameLogic(scanner, galgjeWoorden);
		do {
			
			int keuze = chooseDifficultyLevel();
			returnToMainMenu = gameLogic.gamePlay(keuze);
			
		} while(returnToMainMenu);
	}
	
	private int chooseDifficultyLevel() {
		return new SelectDifficultyLevel(scanner).selectAmountOfLives();
	}
	
}
