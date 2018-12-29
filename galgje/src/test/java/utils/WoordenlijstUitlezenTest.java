package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class WoordenlijstUitlezenTest extends TestCase {
	
	String fileName;
	Map<Integer, String> galgjeWoorden;
	
	@Before
	public void setUp() {
		galgjeWoorden = new HashMap<Integer, String>();
	}

	/**
	 * This method will test if the 4_words_galgje.csv file is correctly loaded.
	 * 
	 * Checks on the size as well as the value of 100 from the words.
	 * @throws IOException
	 */
	@Test
	public void testReadCSV4Words() throws IOException {

		WoordenlijstUitlezen lijstUitlezen = new WoordenlijstUitlezen();
		galgjeWoorden = lijstUitlezen.readsSelectedCSVFile(fileName = "4_words_galgje.csv");
		
		assertEquals(339, galgjeWoorden.size());
		assertEquals("foei", galgjeWoorden.get(100));
		
	}
	
	/**
	 * This method tests if the file can be reached and correctly read.
	 * 
	 * Also checks the value of the 100th value
	 * @throws IOException
	 */
	@Test
	public void testReadCSV5Words() throws IOException {
		WoordenlijstUitlezen woordenlijstUitlezen = new WoordenlijstUitlezen();
		galgjeWoorden = woordenlijstUitlezen.readsSelectedCSVFile(fileName = "5_words_galgje.csv");
		
		assertEquals(445, galgjeWoorden.size());
		assertEquals("elfje", galgjeWoorden.get(100));
		
	}

}
