package hangman.game.save;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.TestUtils;

public class ReadUserGameDataTest {

	private static final String TEST_FILE_NAME = "UserGameDataTest.json";

	@Before
	public void setUp() {
		TestUtils.getInstance().createTestJsonFile(TEST_FILE_NAME);
	}

	@After
	public void tearDown() {
		TestUtils.getInstance().deleteTestFile(TEST_FILE_NAME);
	}

	@Test
	public void readDataCorrect() throws IOException {
		UserGameData userdata = ReadUserGameData.readUserGameData(TEST_FILE_NAME);
		assertEquals(1, userdata.getGamesPlayed());
		assertEquals(1, userdata.getWordsSolved());
		assertEquals(7, userdata.getLivesUsed());
	}

	@Test
	public void newUserIsCreated() throws IOException {
		UserGameData userdata = ReadUserGameData.readUserGameData("InvalidLocation");
		assertEquals(0, userdata.getGamesPlayed());
		assertEquals(0, userdata.getLivesUsed());
		assertEquals(0, userdata.getWordsSolved());
	}

}
