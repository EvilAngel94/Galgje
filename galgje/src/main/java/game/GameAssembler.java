package game;

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
	private int difficulty;
	
	public GameAssembler(Scanner scanner, Map<Integer, String> galgjeWoorden, int difficulty) {
		super();
		this.scanner = scanner;
		this.galgjeWoorden = galgjeWoorden;
		this.difficulty = difficulty;
	}

	/**
	 * This method is responsible for running the game itself.
	 */
	public void runGameLogic() {
		GameLogic gameLogic = new GameLogic(scanner, galgjeWoorden, difficulty);
		gameLogic.gamePlay();
	}
	
}
