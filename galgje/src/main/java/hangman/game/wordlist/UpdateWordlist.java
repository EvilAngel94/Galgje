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
class UpdateWordlist {
	
	private static final Logger LOGGER = LogManager.getLogger(UpdateWordlist.class);

	private UpdateWordlist() {
		super();
	}
	
	public static void updateWordlist(String nameOfTheWordlist, Map<Integer, String> hangmanWords) {
		try (FileWriter csvWriter = new FileWriter(new File("./user_wordlist/" + nameOfTheWordlist))) {

			WordlistUtils.writeUserDefinedWordsToCsv(nameOfTheWordlist, hangmanWords, csvWriter);

			LOGGER.info("Succesfully updated {}", nameOfTheWordlist);
		} catch (IOException e) {
			LOGGER.info("Could not add new word to exising file {} /nStacktrace:{}", nameOfTheWordlist, e.getMessage());
		}
	}

}
