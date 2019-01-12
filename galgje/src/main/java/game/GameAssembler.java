package game;

import java.util.Map;
import java.util.Scanner;

/**
 * This class is responsible to combine all the information gatherd for the startup of the game.
 * As well as processing the incomming data and distributing this to the other components.
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
		GameLogic gameLogic = new GameLogic(scanner, galgjeWoorden);
		gameLogic.gamePlay();
	}
	
	
}
