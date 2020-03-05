package hangman.game.wordlist;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import hangman.game.wordlist.WordListInteraction;

public class WordListInteractionTest {

	private Map<Integer, String> galgjeWoorden = new HashMap<>();

	@Test
	public void readCSV4WordsDutch() throws IOException {
		galgjeWoorden = new WordListInteraction().readsSelectedCSVFile("4_words_dutch.csv");
		assertEquals(60, galgjeWoorden.size());
	}

	@Test
	public void readCSV5WordsDutch() throws IOException {
		galgjeWoorden = new WordListInteraction().readsSelectedCSVFile("5_words_dutch.csv");
		assertEquals(60, galgjeWoorden.size());
	}

	@Test
	public void readCsv4WordEnglish() throws IOException {
		galgjeWoorden = new WordListInteraction().readsSelectedCSVFile("4_words_english.csv");
		assertEquals(60, galgjeWoorden.size());
	}

	@Test
	public void readCsv5WordEnglish() throws IOException {
		galgjeWoorden = new WordListInteraction().readsSelectedCSVFile("5_words_english.csv");
		assertEquals(60, galgjeWoorden.size());
	}

	@Test
	public void addNewWordToCsvFile() throws IOException {
		WordListInteraction interactions = new WordListInteraction();
		interactions.addNewWordToCsvList("4_words_english.csv", "TestTest");
		galgjeWoorden = interactions.readsSelectedCSVFile("4_words_english.csv");

		assertEquals(61, galgjeWoorden.size());
	}

	private Map<Integer, String> createStubMap() {
		for (int i = 0; i < 60; i++) {
			galgjeWoorden.put(i, "test_" + i);
		}
		return galgjeWoorden;
	}
}