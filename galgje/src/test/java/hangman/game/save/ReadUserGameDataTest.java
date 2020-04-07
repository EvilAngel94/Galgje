package hangman.game.save;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.TestUtils;

public class ReadUserGameDataTest {

	private static final String TEST_FILE_NAME = "UserGameDataTest.json";

	@Before
	public void setup() {
		TestUtils.getInstance().createTestJsonFile(TEST_FILE_NAME);
	}

	@After
	public void teardown() {
		TestUtils.getInstance().deleteTestFile(TEST_FILE_NAME);
	}

	@Test
	public void readDataCorrect() {

		UserGameData userdata = new ReadUserGameData().readUserGameData(TEST_FILE_NAME);
//		assertEquals(1, userdata.getGamesPlayed());
		assertEquals(10, userdata.getWordsSolved());
		assertEquals(10, userdata.getLivesUsed());
	}

}
