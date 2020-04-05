package hangman.game.wordlist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.TestUtils;

public class DeleteWordFromWordlistTest {

	private String nameOfTheFile;
	private Map<Integer, String> galgjeWoorden;

	@Before
	public void setup() throws IOException {
		nameOfTheFile = TestUtils.getInstance().createTestCsvFile();
	}
	
	@After
	public void teardown() {
		TestUtils.getInstance().deleteTestCsvFile();
	}

	@Test
	public void deleteWordFormFile() throws IOException {
		galgjeWoorden = WordlistInteractions.readUserDefinedWordlist(nameOfTheFile);
		assertEquals(60, galgjeWoorden.size());

		DeleteWordFromWordList.deleteWordFromCsvList(nameOfTheFile, galgjeWoorden.get(galgjeWoorden.size() - 1));

		galgjeWoorden = WordlistInteractions.readUserDefinedWordlist(nameOfTheFile);
		assertEquals(59, galgjeWoorden.size());
	}

	@Test
	public void deleteNonExistingWord() throws IOException {
		galgjeWoorden = WordlistInteractions.readUserDefinedWordlist(nameOfTheFile);
		assertEquals(60, galgjeWoorden.size());

		// Word can't be found as there is no word in that list
		DeleteWordFromWordList.deleteWordFromCsvList(nameOfTheFile, galgjeWoorden.get(galgjeWoorden.size() + 1));

		galgjeWoorden = WordlistInteractions.readUserDefinedWordlist(nameOfTheFile);
		assertEquals(60, galgjeWoorden.size());
	}

	@Test
	public void nonExitingFile() {
		galgjeWoorden = WordlistInteractions.readUserDefinedWordlist("InvalidLocation");
		assertNull(galgjeWoorden);
	}

}
