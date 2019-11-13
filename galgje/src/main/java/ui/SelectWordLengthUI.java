package ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import utils.Validate;
import utils.WordListReader;

/**
 * This class is responsible to select the right file to be chosen from.
 *
 */
public class SelectWordLengthUI {

	private static final String WORDS_GALGJE_FOUR = "4_words_galgje.csv";
	private static final String WORDS_GALGJE_FIVE = "5_words_galgje.csv";

	private final Scanner scanner;

	private WordListReader uitlezen;
	private Validate valideer;

	public SelectWordLengthUI(Scanner scanner) {
		super();
		this.scanner = scanner;
		uitlezen = new WordListReader();
		valideer = new Validate(scanner);
	}

	/**
	 * This method loads the wordList based on the input given.
	 * 
	 * @return an HashMap from the 
	 * 
	 * @throws IOException
	 */
	public Map<Integer, String> loadWordList() throws IOException {
		
		Map<Integer, String> galgjeWoorden = new HashMap<>();
		System.out.println("Selecteer nu je woordenLengte [1] = 4, [2] = 5 letter woorden");
		
		int waarde = valideer.valideerWoordlengteKeuze();
		if (waarde == 1) {
			galgjeWoorden = uitlezen.readsSelectedCSVFile(WORDS_GALGJE_FOUR);
			return galgjeWoorden;
		}
		if (waarde == 2) {
			galgjeWoorden = uitlezen.readsSelectedCSVFile(WORDS_GALGJE_FIVE);
			return galgjeWoorden;
		}
		System.out.println("Sorry please provide a valid input");
		return loadWordList();
	}

}
