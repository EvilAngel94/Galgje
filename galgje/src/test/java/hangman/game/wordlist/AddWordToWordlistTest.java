package hangman.game.wordlist;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.TestUtils;

public class AddWordToWordlistTest {

	private static final String LOCATION_TESTFILE = "./user_wordlist/test.csv";
	private String nameOfTheFile;

	@Before
	public void setup() {
		nameOfTheFile = TestUtils.getInstance().createTestCsvFile();
	}

	@After
	public void teardown() {
		TestUtils.getInstance().deleteTestFile(LOCATION_TESTFILE);	
	}

	@Test
	public void addNewWordToCsvFile() throws IOException {
		AddWordToWordList.addNewWordToCsvList(nameOfTheFile, "TestTest");
		Map<Integer, String> galgjeWoorden = WordlistInteractions.readUserDefinedWordlist(nameOfTheFile);
		assertEquals(61, galgjeWoorden.size());
	}

}
