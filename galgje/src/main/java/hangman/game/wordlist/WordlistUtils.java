package hangman.game.wordlist;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class WordlistUtils {

	private static final Logger LOGGER = LogManager.getLogger(WordlistUtils.class);

	private WordlistUtils() {
		super();
	}

	protected static void updateCsvFileWithAllWords(String nameOfTheWordlist, Map<Integer, String> hangmanWords,
			FileWriter csvWriter) throws IOException {

		writeAllWordsToCsvFile(nameOfTheWordlist, hangmanWords, csvWriter, null);
	}

	protected static void writeUserDefinedWordToCsv(String nameOfTheWordList, Map<Integer, String> hangmanWords,
			String wordToAdd, FileWriter csvWriter) throws IOException {

		String entityCombination = ((hangmanWords.size() + 1) + "") + "," + wordToAdd;
		writeAllWordsToCsvFile(nameOfTheWordList, hangmanWords, csvWriter, entityCombination);
	}

	private static void writeAllWordsToCsvFile(String nameOfTheWordlist, Map<Integer, String> hangmanWords,
			FileWriter csvWriter, String entryCombination) throws IOException {

		String naam = nameOfTheWordlist.contains("dutch") ? "nederlands" : "english";
		csvWriter.append("id," + naam + "\n");

		hangmanWords.forEach((key, value) -> {
			try {
				csvWriter.append((key + "") + "," + value + "\n");
			} catch (IOException e) {
				LOGGER.debug("Could not append {} {}. /nStacktrace: {}", key, value, e);
			}
		});

		if (entryCombination != null) {
			csvWriter.append(entryCombination);
			LOGGER.info("Succesfully added word to csv file {}", entryCombination);
		}
		
		csvWriter.flush();
	}

}
