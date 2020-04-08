package hangman.game.save;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

public class SaveUserGameData {
	private static final Logger LOGGER = LogManager.getLogger(SaveUserGameData.class);

	private UserGameData userGameData;

	public SaveUserGameData(UserGameData userGameData) {
		super();
		this.userGameData = userGameData;
	}

	public boolean saveUserGameData(String fileName) throws IOException {
		Gson gson = new Gson();

		if (!userGameData.isValid()) {
			LOGGER.error("UserGameData is not valid: {}", userGameData);
			return false;
		}
		
		UserGameData oldUserGameData = new ReadUserGameData().readUserGameData(fileName);
		userGameData = addOldGameDataToNewGameData(oldUserGameData);
		
		try (FileWriter writer = new FileWriter(new File(fileName))) {

			gson.toJson(userGameData, writer);

		} catch (IOException e) {
			LOGGER.error("IOException {}", e);
			throw e;
		}

		return true;
	}

	private UserGameData addOldGameDataToNewGameData(UserGameData oldUserGameData) throws IOException {
		userGameData.setGamesPlayed(oldUserGameData.getGamesPlayed() + 1);
		userGameData.setLivesUsed(userGameData.getLivesUsed() + oldUserGameData.getLivesUsed());
		userGameData.setWordsSolved(userGameData.getWordsSolved() + oldUserGameData.getWordsSolved());
		
		return userGameData;
	}
}
