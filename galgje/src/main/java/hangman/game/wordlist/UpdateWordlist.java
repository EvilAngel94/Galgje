package hangman.game.wordlist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is responsible for updating the wordlist after a deletion.
 * 
 * @author Michiel de Smet
 *
 */
public class UpdateWordlist {
	
	private static final Logger LOGGER = LogManager.getLogger(UpdateWordlist.class);

	private UpdateWordlist() {
		super();
	}
	
	public static void updateWordlist(String nameOfTheWordList, Map<Integer, String> hangmanWords) {
		try (FileWriter csvWriter = new FileWriter(new File("./user_wordlist/" + nameOfTheWordList))) {

			String naam = nameOfTheWordList.contains("dutch") ? "nederlands" : "english";

			csvWriter.append("id," + naam + "\n");

			hangmanWords.forEach((key, value) -> {
				try {
					csvWriter.append((key + "") + "," + value + "\n");
				} catch (IOException e) {
					LOGGER.debug("Could not append {} {}. /nStacktrace: {}", key, value, e);
				}
			});

			csvWriter.flush();

			LOGGER.info("Succesfully updated {}", nameOfTheWordList);
		} catch (IOException e) {
			LOGGER.info("Could not add new word to exising file {} /nStacktrace:{}", nameOfTheWordList, e.getMessage());
		}
	}
}
