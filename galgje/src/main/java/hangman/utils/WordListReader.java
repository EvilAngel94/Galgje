package hangman.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Deze klasse leest de woorden uit van het {@link galgje_woorden_lijst}
 * resources.
 * 
 * Aan de hand van de keuze die de speler maakt word de juiste woordenlijst
 * geladen.
 * 
 * The following 2 classes are used to read the file.
 * {@link https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html}
 * {@link https://docs.oracle.com/javase/8/docs/api/java/io/FileReader.html}
 *
 */
public class WordListReader {
	
	private static final Logger LOGGER = LogManager.getLogger(WordListReader.class);

	private Map<Integer, String> hangmanWords;

	public WordListReader() {
		super();
		hangmanWords = new HashMap<>();
	}

	/**
	 * The wordt list is being read read here. All the found words are put in a map, based on their number and word.
	 * 
	 * @param wordlist
	 * 
	 * @return Map of possible words to play with.
	 * 
	 * @throws IOException
	 */
	public Map<Integer, String> readsSelectedCSVFile(String wordlist) throws IOException {
		LOGGER.info("Wordlist is being loaded with the following name: {}", wordlist);
		//this refers to this class.
		InputStream inputStream = this.getClass().getResourceAsStream("/hangman_word_list/" + wordlist);
		InputStreamReader inputReader = new InputStreamReader(inputStream);
		
		
		try (BufferedReader reader = new BufferedReader(inputReader)){

			String seperator = ",";
			
			//first line should be ignored, that's why the value is not used
			String line = reader.readLine(); 

			// This checks if the reader has a next line.
			while ((line = reader.readLine()) != null) {

				String[] field = line.split(seperator);
				int id = Integer.parseInt(field[0]);
				String word = field[1];

				hangmanWords.put(id, word);
			}
		} catch (FileNotFoundException e) {
			LOGGER.error("File with name {} cannot be found! Make sure the name is spelled correctly. error: {}", wordlist, e);
		}
		
		return hangmanWords;
	}
}
