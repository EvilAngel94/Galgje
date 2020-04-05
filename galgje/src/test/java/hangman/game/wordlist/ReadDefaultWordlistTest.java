package hangman.game.wordlist;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ReadDefaultWordlistTest {

	private Map<Integer, String> galgjeWoorden = new HashMap<>();

	@Test
	public void readCSV4WordsDutch() throws IOException {
		galgjeWoorden = WordlistInteractions.readDefaultWordlist("4_words_dutch.csv");
		assertEquals(60, galgjeWoorden.size());
	}

	@Test
	public void readCSV5WordsDutch() throws IOException {
		galgjeWoorden = WordlistInteractions.readDefaultWordlist("5_words_dutch.csv");
		assertEquals(60, galgjeWoorden.size());
	}

	@Test
	public void readCsv4WordEnglish() throws IOException {
		galgjeWoorden = WordlistInteractions.readDefaultWordlist("4_words_english.csv");
		assertEquals(60, galgjeWoorden.size());
	}

	@Test
	public void readCsv5WordEnglish() throws IOException {
		galgjeWoorden = WordlistInteractions.readDefaultWordlist("5_words_english.csv");
		assertEquals(60, galgjeWoorden.size());
	}
}