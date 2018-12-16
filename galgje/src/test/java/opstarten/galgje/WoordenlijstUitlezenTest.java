package opstarten.galgje;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;

import junit.framework.TestCase;

public class WoordenlijstUitlezenTest extends TestCase {
	
	private String fileName;
	private Map<Integer, String> galgjeWoorden;
	
	@Before
	public void setUp() {
		fileName = "4_words_galgje.csv";
		galgjeWoorden = new HashMap<Integer, String>();
	}

	public void testReadsSelectedCSVFile() throws IOException {
		WoordenlijstUitlezen lijstUitlezen = new WoordenlijstUitlezen();
		galgjeWoorden = lijstUitlezen.readsSelectedCSVFile(fileName);
		
		assertEquals(339, galgjeWoorden.size());
		assertEquals("foei", galgjeWoorden.get(100));
		
	}

}
