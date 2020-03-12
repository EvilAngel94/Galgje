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
public class AddWordToWordList {

	private static final Logger LOGGER = LogManager.getLogger(AddWordToWordList.class);

	private ReadWordList reader = new ReadWordList();

	/** Constructor */
	public AddWordToWordList() {
		super();
	}

	public void addNewWordToCsvList(String nameOfTheWordList, String wordToAdd) throws IOException {
		LOGGER.info("{} tries to be added to {}", wordToAdd, nameOfTheWordList);

		Map<Integer, String> hangmanWords = reader.readUserDefinedWordList(nameOfTheWordList);

		addNewWordToCsvFile(nameOfTheWordList, hangmanWords, wordToAdd);
	}

	private void addNewWordToCsvFile(String nameOfTheWordList, Map<Integer, String> hangmanWords, String wordToAdd) {
		int numberKey = hangmanWords.size() + 1;

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

			String entityCombination = (numberKey + "") + "," + wordToAdd;
			csvWriter.append(entityCombination);
			csvWriter.flush();

			LOGGER.info("Succesfully added word to csv file {}", entityCombination);
		} catch (IOException e) {
			LOGGER.info("Could not add new word to exising file {} /nStacktrace:{}", nameOfTheWordList, e.getMessage());
		}
	}
}
