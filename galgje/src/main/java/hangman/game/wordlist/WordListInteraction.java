package hangman.game.wordlist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

	private Map<Integer, String> hangmanWords;

	public WordListInteraction() {
		super();
		hangmanWords = new HashMap<>();
	}

	/**
	 * The word list is being read read here. All the found words are put in a map,
	 * based on their number and word.
	 * 
	 * @param nameOfTheWordList
	 * 
	 * @return Map of possible words to play with.
	 * 
	 * @throws IOException
	 */
	public Map<Integer, String> readsSelectedCSVFile(String nameOfTheWordList) throws IOException {
		LOGGER.debug("Wordlist is being loaded with the following name: {}", nameOfTheWordList);
		// this refers to this class.
		InputStream inputStream = this.getClass().getResourceAsStream("/hangman_word_list/" + nameOfTheWordList);
		InputStreamReader inputReader = new InputStreamReader(inputStream);

		try (BufferedReader reader = new BufferedReader(inputReader)) {

			String seperator = ",";

			// first line should be ignored, that's why the value is not used
			String line = reader.readLine();

			// This checks if the reader has a next line.
			while ((line = reader.readLine()) != null) {

				String[] field = line.split(seperator);
				int id = Integer.parseInt(field[0]);
				String word = field[1];

				hangmanWords.put(id, word);
			}
		} catch (FileNotFoundException e) {
			LOGGER.error("File with name {} cannot be found! Make sure the name is spelled correctly. error: {}",
					nameOfTheWordList, e);
		}
		return hangmanWords;
	}

	public void addNewWordToCsvList(String nameOfTheWordList, String wordToAdd) throws IOException {
		//Je kan niet opeens nieuwe woordenlijsten updaten die in src/main/resources zitten.. Dus moet je deze in je root folder doen denk ik. Of in een apart mapje.. 
		//Moet je even goed kijken. Het gaat in ieder geval wel goed ;)
		LOGGER.info("{} tries to be added to {}", nameOfTheWordList, wordToAdd);

		hangmanWords = readsSelectedCSVFile(nameOfTheWordList);
		int size = hangmanWords.size();
		addNewWordToCsvFile(nameOfTheWordList, size + 1, wordToAdd);

		hangmanWords = readsSelectedCSVFile(nameOfTheWordList);
	}

	private boolean addNewWordToCsvFile(String nameOfTheWordList, int numberKey, String wordToAdd) {
		try (FileWriter csvWriter = new FileWriter(
				new File(this.getClass().getResource("/hangman_word_list/" + nameOfTheWordList).getPath()))) {
			
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
