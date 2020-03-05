package hangman.utils;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import hangman.utils.WordListReader;

public class WoordenlijstUitlezenTest {

	private Map<Integer, String> galgjeWoorden = new HashMap<>();

	@Test
	public void readCSV4WordsDutch() throws IOException {
		galgjeWoorden = new WordListReader().readsSelectedCSVFile("4_words_dutch.csv");
		assertEquals(60, galgjeWoorden.size());
	}

	@Test
	public void readCSV5WordsDutch() throws IOException {
		galgjeWoorden = new WordListReader().readsSelectedCSVFile("5_words_dutch.csv");
		assertEquals(60, galgjeWoorden.size());
	}
	
	@Test
	public void readCsv4WordEnglish() throws IOException {
		galgjeWoorden = new WordListReader().readsSelectedCSVFile("4_words_english.csv");
		assertEquals(60, galgjeWoorden.size());
	}
	
	@Test
	public void readCsv5WordEnglish() throws IOException {
		galgjeWoorden = new WordListReader().readsSelectedCSVFile("5_words_english.csv");
		assertEquals(60, galgjeWoorden.size());
	}
	
	private Map<Integer,String> createStubMap(){
		for(int i =0; i < 60; i++) {
			galgjeWoorden.put(i, "test_" + i);
		}
		return galgjeWoorden;
	}
}