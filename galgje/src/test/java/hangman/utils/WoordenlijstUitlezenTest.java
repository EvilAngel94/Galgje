package hangman.utils;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import hangman.utils.WordListReader;

public class WoordenlijstUitlezenTest{
	
	private Map<Integer, String> galgjeWoorden = new HashMap<>();
	
	@Test
	public void testReadCSV4Words() throws IOException {
		galgjeWoorden = new WordListReader().readsSelectedCSVFile("4_words_galgje.csv");
		
		assertEquals(339, galgjeWoorden.size());
		assertEquals("foei", galgjeWoorden.get(100));
	}
	
	@Test
	public void testReadCSV5Words() throws IOException {
		galgjeWoorden = new WordListReader().readsSelectedCSVFile("5_words_galgje.csv");
		
		assertEquals(445, galgjeWoorden.size());
		assertEquals("elfje", galgjeWoorden.get(100));
	}
}