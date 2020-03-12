package hangman.game.wordlist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteWordFromWordList {

	private static final Logger LOGGER = LogManager.getLogger(DeleteWordFromWordList.class);

	public DeleteWordFromWordList() {
		super();
	}

	public void deleteWordFromCsvList(String nameOfTheWordList, String wordToDelete) {
		LOGGER.info("Trying to delete {} from file {}", wordToDelete, nameOfTheWordList);
		//Get all the words
		
		// Go through and see if word exist
		
		// if exist delete, otherwise don't do anything.
	}
}
