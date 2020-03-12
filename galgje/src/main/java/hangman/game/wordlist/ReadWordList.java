package hangman.game.wordlist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadWordList {
	private static final Logger LOGGER = LogManager.getLogger(ReadWordList.class);

	private Map<Integer, String> hangmanWords;

	public ReadWordList() {
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
	public Map<Integer, String> readDefaultWordList(String nameOfTheWordList) throws IOException {
		LOGGER.debug("Wordlist is being loaded with the following name: {}", nameOfTheWordList);
		// this refers to this class.
		InputStream inputStream = this.getClass().getResourceAsStream("/hangman_word_list/" + nameOfTheWordList);
		InputStreamReader inputReader = new InputStreamReader(inputStream);

		try (BufferedReader reader = new BufferedReader(inputReader)) {

			addWordsToHangmanMap(reader);
			
		} catch (FileNotFoundException e) {
			LOGGER.error("File with name {} cannot be found! Make sure the name is spelled correctly. error: {}",
					nameOfTheWordList, e);
		}
		return hangmanWords;
	}

	/**
	 * This method is responsible for reading user created wordlists. These lists have a default 50 words, and possible addidional words added by the user.
	 * 
	 * @param nameOfTheWordList
	 * 
	 * @return map of possible words to play with
	 * 
	 * @throws IOException
	 */
	public Map<Integer, String> readUserDefinedWordList(String nameOfTheWordList) throws IOException {
		File file = new File("./user_wordlist/" + nameOfTheWordList);
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			addWordsToHangmanMap(reader);
			
		} catch (FileNotFoundException e) {
			LOGGER.error("File with name {} cannot be found! Make sure the name is spelled correctly. error: {}",
					nameOfTheWordList, e);
		}
		return hangmanWords;

	}

	private void addWordsToHangmanMap(BufferedReader reader) throws IOException {
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
	}
}
