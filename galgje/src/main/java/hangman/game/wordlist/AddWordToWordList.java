package hangman.game.wordlist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
class AddWordToWordList {

	private static final Logger LOGGER = LogManager.getLogger(AddWordToWordList.class);

	private AddWordToWordList() {
		super();
	}

	public static void addNewWordToCsvList(String nameOfTheWordList, String wordToAdd) throws IOException {
		LOGGER.info("{} tries to be added to {}", wordToAdd, nameOfTheWordList);

		Map<Integer, String> hangmanWords = new ReadWordList().readUserDefinedWordList(nameOfTheWordList);

		addNewWordToCsvFile(nameOfTheWordList, hangmanWords, wordToAdd);
	}

	private static void addNewWordToCsvFile(String nameOfTheWordList, Map<Integer, String> hangmanWords, String wordToAdd) {
		try (FileWriter csvWriter = new FileWriter(new File("./user_wordlist/" + nameOfTheWordList))) {

			WordlistUtils.writeUserDefinedWordsToCsv(nameOfTheWordList, hangmanWords, wordToAdd, csvWriter);
			
		} catch (IOException e) {
			LOGGER.info("Could not add new word to exising file {} /nStacktrace:{}", nameOfTheWordList, e.getMessage());
		}
	}

}
