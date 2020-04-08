package hangman.game.save;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

public class ReadUserGameData {

	private static final Logger LOGGER = LogManager.getLogger(ReadUserGameData.class);

	public ReadUserGameData() {
		super();
	}

	public UserGameData readUserGameData(String fileName) throws IOException {
		UserGameData userGameData = null;
		Gson gson = new Gson();
		
		File file = new File(fileName);
		
		if(!file.exists()) {
			return new UserGameData();
		}
		
		try (Reader reader = new FileReader(fileName)) {
			
			userGameData = gson.fromJson(reader, UserGameData.class);

		} catch (IOException ex) {
			LOGGER.error("Error while reading userdata {}", ex);
			throw ex;
		}

		return userGameData;
	}

}
