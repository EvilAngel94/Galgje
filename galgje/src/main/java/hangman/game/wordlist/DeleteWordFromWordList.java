package hangman.game.wordlist;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class DeleteWordFromWordList {

	private static final Logger LOGGER = LogManager.getLogger(DeleteWordFromWordList.class);

	private DeleteWordFromWordList() {
		super();
	}

	public static void deleteWordFromCsvList(String nameOfTheWordList, String wordToDelete) throws IOException {
		LOGGER.info("Trying to delete {} from file {}", wordToDelete, nameOfTheWordList);

		Map<Integer, String> hangmanwords = new ReadWordList().readUserDefinedWordList(nameOfTheWordList);

		Optional<Entry<Integer, String>> foundEntry = hangmanwords.entrySet().stream()
				.filter(entry -> entry.getValue().equals(wordToDelete)).findAny();

		if (foundEntry.isPresent()) {
			Entry<Integer, String> hangmanword = foundEntry.get();
			hangmanwords.remove(hangmanword.getKey());
			UpdateWordlist.updateWordlist(nameOfTheWordList, hangmanwords);
		}
	}
}
