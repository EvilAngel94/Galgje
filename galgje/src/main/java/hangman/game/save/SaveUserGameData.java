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

	public boolean saveUserGameData(String filename) throws IOException {
		Gson gson = new Gson();

		if (!userGameData.isValid()) {
			LOGGER.error("UserGameData is not valid: {}", userGameData);
			return false;
		}
		
		try (FileWriter writer = new FileWriter(new File(filename))) {

			gson.toJson(userGameData, writer);

		} catch (IOException e) {
			LOGGER.error("IOException {}", e);
			throw e;
		}

		return true;
	}
}
