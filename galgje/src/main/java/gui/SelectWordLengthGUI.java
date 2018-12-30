package gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import utils.WoordenlijstUitlezen;

/**
 * This class is responsible to select the right file to be chosen from.
 *
 */
public class SelectWordLengthGUI {

	private static final String WORDS_GALGJE_FOUR = "4_words_galgje.csv";
	private static final String WORDS_GALGJE_FIVE = "5_words_galgje.csv";

	private final Scanner scanner;

	private WoordenlijstUitlezen uitlezen;

	public SelectWordLengthGUI(Scanner scanner) {
		super();
		this.scanner = scanner;
		uitlezen = new WoordenlijstUitlezen();
	}

	/**
	 * This method loads the wordList based on the input given.
	 * 
	 * @return an HashMap from the 
	 * 
	 * @throws IOException
	 */
	public HashMap<Integer, String> loadWordList() throws IOException {
		
		HashMap<Integer, String> galgjeWoorden = new HashMap<Integer, String>();
		System.out.println("Selecteer nu je woordenLengte [1] = 4, [2] = 5 letter woorden");
		
		int waarde = scanner.nextInt();
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
