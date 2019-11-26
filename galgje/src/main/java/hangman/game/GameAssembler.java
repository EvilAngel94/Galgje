package hangman.game;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hangman.ui.SelectWordLengthUI;

/**
 * This class is responsible to combine all the information gathered for the
 * startup of the game. As well as processing the incoming data and distributing
 * this to the other components.
 * 
 * {@link GameLogic}
 * 
 * @author PolarBear Dev
 *
 */
public class GameAssembler {

	private static final Logger LOGGER = LogManager.getLogger(GameAssembler.class);

	private final Scanner scanner;
	
	private boolean isDutch;

	public GameAssembler(Scanner scanner, boolean isDutch) {
		super();
		this.scanner = scanner;
		this.isDutch = isDutch;
	}

	/**
	 * This method is responsible for running the game itself. Furthermore all the
	 * logic is combined within this method. From choosing the difficulty level, to
	 * the list of words to be played with.
	 */
	public void runGameLogic() {
		int returnToMainMenu = 0;

		GameLogic gameLogic = new GameLogic(scanner, retreiveHangmanWords(), isDutch);
		
		do {
			
			if(returnToMainMenu == 3) {
				gameLogic = new GameLogic(scanner, retreiveHangmanWords(), isDutch);
			}

			int keuze = chooseDifficultyLevel();
			returnToMainMenu = gameLogic.gamePlay(keuze);

		} while (returnToMainMenu != 2);
	}

	private int chooseDifficultyLevel() {
		return new SelectDifficultyLevel(scanner).selectAmountOfLives();
	}

	private Map<Integer, String> retreiveHangmanWords() {

		SelectWordLengthUI selectLengthGUI = new SelectWordLengthUI(scanner, isDutch);
		Map<Integer, String> hangmanWords = new HashMap<>();

		try {
			hangmanWords = selectLengthGUI.loadWordList();

		} catch (IOException e) {
			LOGGER.error("Game is stoped, something went wrong while loading the words from the file. IOError: {}", e);
		}
		
		return hangmanWords;
	}

}
