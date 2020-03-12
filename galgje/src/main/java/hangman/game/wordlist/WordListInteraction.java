package hangman.game.wordlist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is responsible for reading, updating and deleting words from the
 * selected word list.
 * 
 * @author PolarBear Dev
 *
 */
public class WordListInteraction {

	private static final Logger LOGGER = LogManager.getLogger(WordListInteraction.class);

	private ReadWordList reader = new ReadWordList();

	/** Constructor */
	public WordListInteraction() {
		super();
	}

	public void addNewWordToCsvList(String nameOfTheWordList, String wordToAdd) throws IOException {
		LOGGER.info("{} tries to be added to {}", nameOfTheWordList, wordToAdd);
		Map<Integer, String> hangmanWords = null;

		hangmanWords = reader.readDefaultWordList(nameOfTheWordList);

		addNewWordToCsvFile(nameOfTheWordList, hangmanWords.size() + 1, wordToAdd);
	}

	private boolean addNewWordToCsvFile(String nameOfTheWordList, int numberKey, String wordToAdd) {
		try (FileWriter csvWriter = new FileWriter(new File("user_wordlist" + nameOfTheWordList))) {

			String entityCombination = (numberKey + "") + "," + wordToAdd;
			csvWriter.append("\n");
			csvWriter.append(entityCombination);
			csvWriter.flush();

			LOGGER.info("Succesfully added word to csv file {}", entityCombination);
			return true;
		} catch (IOException e) {
			LOGGER.debug("Could not add new word to exising file {} /nStacktrace:{}", nameOfTheWordList,
					e.getMessage());
			return false;
		}
	}

	public void deleteWordFromCsvList(String nameOfTheWordList, String wordToDelete) {
		LOGGER.info("Trying to add {} to file {}", wordToDelete, nameOfTheWordList);

	}

}
