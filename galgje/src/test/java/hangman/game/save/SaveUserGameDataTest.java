package hangman.game.save;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import utils.TestUtils;

public class SaveUserGameDataTest {

	private static final String TEST_FILE_NAME = "UserGameDataTest.json";

	private SaveUserGameData saveUserGameData;

	@After
	public void teardown() {
		TestUtils.getInstance().deleteTestFile(TEST_FILE_NAME);
	}

	@Test
	public void saveUserGameDataCorrectly() throws IOException {
		saveUserGameData = new SaveUserGameData(new UserGameData(9, 10));
		assertTrue(saveUserGameData.saveUserGameData(TEST_FILE_NAME));
	}

	@Test
	public void saveNewGameDataToOldData() throws IOException {
		UserGameData userGameData = new UserGameData(1, 5);
		saveUserGameData = new SaveUserGameData(userGameData);
		assertTrue(saveUserGameData.saveUserGameData(TEST_FILE_NAME));

		userGameData = new UserGameData(1, 5);
		saveUserGameData = new SaveUserGameData(userGameData);
		assertTrue(saveUserGameData.saveUserGameData(TEST_FILE_NAME));

		userGameData = ReadUserGameData.readUserGameData(TEST_FILE_NAME);
		assertEquals(2, userGameData.getWordsSolved());
		assertEquals(2, userGameData.getGamesPlayed());
		assertEquals(10, userGameData.getLivesUsed());
	}

}
