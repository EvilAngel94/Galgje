package hangman.game.save;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import utils.TestUtils;

public class SaveUserGameDataTest {
	
	private static final String TEST_FILE_NAME = "UserGameDataTest.json";
	
	@After
	public void teardown() {
		TestUtils.getInstance().deleteTestFile(TEST_FILE_NAME);
	}

	@Test
	public void test() throws IOException {
		SaveUserGameData saveUserGameData= new SaveUserGameData(new UserGameData(9, 10));
		assertTrue(saveUserGameData.saveUserGameData(TEST_FILE_NAME));
	}

}
