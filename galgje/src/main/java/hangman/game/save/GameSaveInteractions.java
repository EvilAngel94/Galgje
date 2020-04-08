package hangman.game.save;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hangman.utils.PropertyReader;

public class GameSaveInteractions {

	private static final Logger LOGGER = LogManager.getLogger(GameSaveInteractions.class);

	private GameSaveInteractions() {
		super();
	}

	public static void saveUserGameData(UserGameData userGameData) {
		LOGGER.info("Trying to save data of the latest game. \nStats: {}", userGameData);
		try {
			new SaveUserGameData(userGameData).saveUserGameData(PropertyReader.getInstance().getProperty("fileName"));

		} catch (IOException e) {
			LOGGER.error("Something went wrong while saving the data. {}", e);
		}
	}

	public static UserGameData readUserGameData() {
		LOGGER.debug("Trying to read the game data..");
		UserGameData userGameData = null;
		try {
			userGameData = ReadUserGameData.readUserGameData(PropertyReader.getInstance().getProperty("fileName"));
			
		} catch (IOException e) {
			LOGGER.error("Could not read the saved game data. {}", e);
		}
		return userGameData;
	}

}
