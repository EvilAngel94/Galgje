package hangman.game.wordlist;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is responsible for delgating all the interactions needed to read,
 * update, add or delete words to the program.
 * 
 * @author Michiel de Smet
 *
 */
public class WordlistInteractions {

	private static final Logger LOGGER = LogManager.getLogger(WordlistInteractions.class);

	private WordlistInteractions() {
		super();
	}

	public static void addNewWordToList(String nameOfTheWordList, String wordToAdd) {
		try {
			AddWordToWordList.addNewWordToCsvList(nameOfTheWordList, wordToAdd);

		} catch (IOException e) {
			LOGGER.error("Something went wrong while trying to add a new word. /nStacktrace: {}", e);
		}
	}

	public static void deleteWordFromUserDefinedWordlist(String nameOfTheWordList, String wordToDelete) {
		try {
			DeleteWordFromWordList.deleteWordFromCsvList(nameOfTheWordList, wordToDelete);

		} catch (IOException e) {
			LOGGER.error("Something went wrong while trying to delete a word. /nStacktrace: {}", e);
		}
	}

	public static void updateWordlist(String nameOfTheWordList, Map<Integer, String> hangmanWords) {
		try {
			UpdateWordlist.updateWordlist(nameOfTheWordList, hangmanWords);
			
		} catch (IOException e) {
			LOGGER.error("Could not update wordlist {}. /nStacktrace: {}", nameOfTheWordList, e);
		}
	}

	public static Map<Integer, String> readDefaultWordlist(String nameOfTheWordList) {
		Map<Integer, String> hangmanWords = null;
		try {
			hangmanWords = new ReadWordList().readDefaultWordList(nameOfTheWordList);

		} catch (IOException e) {
			LOGGER.error("Something went wrong while trying to read the default wordlist [{}]. /nStacktrace: {}",
					nameOfTheWordList, e);
		}
		return hangmanWords;
	}

	public static Map<Integer, String> readUserDefinedWordlist(String nameOfTheWordList) {
		Map<Integer, String> hangmanWords = null;
		try {
			hangmanWords = new ReadWordList().readUserDefinedWordList(nameOfTheWordList);

		} catch (IOException e) {
			LOGGER.error("Something went wrong while trying to read user defined wordlist [{}]. /nStacktrace: {}",
					nameOfTheWordList, e);
		}
		return hangmanWords;
	}
}
