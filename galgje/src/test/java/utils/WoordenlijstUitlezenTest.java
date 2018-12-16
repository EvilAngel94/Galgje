package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;

import junit.framework.TestCase;
import utils.WoordenlijstUitlezen;

public class WoordenlijstUitlezenTest extends TestCase {
	
	private String fileName;
	private Map<Integer, String> galgjeWoorden;
	
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
	public void testReadsSelectedCSVFile() throws IOException {

		WoordenlijstUitlezen lijstUitlezen = new WoordenlijstUitlezen();
		galgjeWoorden = lijstUitlezen.readsSelectedCSVFile(fileName = "4_words_galgje.csv");
		
		assertEquals(339, galgjeWoorden.size());
		assertEquals("foei", galgjeWoorden.get(100));
		
	}

}
