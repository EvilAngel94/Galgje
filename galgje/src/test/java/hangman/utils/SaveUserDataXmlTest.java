package hangman.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Test;

import hangman.game.save.UserData;

public class SaveUserDataXmlTest {

	private UserData userData;
	private SaveUserDataToXml saveUserData;

	@After
	public void tearDown() {
		new File("userDataUnitTest.xml").deleteOnExit();
	}

	@Test
	public void saveUserDataCorrect() throws InterruptedException {
		userData = new UserData(9, 10);
		userData.setGamesPlayed(8);
		saveUserData = new SaveUserDataToXml(userData);
		assertTrue(saveUserData.saveData(true));
		// assertTrue(TestUtils.getInstance().rowsFoundInFile(new
		// File("userDataUnitTest.xml"), Arrays.asList(
		// "<gamesPlayed>8</gamesPlayed>", "<wordsSolved>1</wordsSolved>",
		// "<livesUsed>10</livesUsed>")));
	}

	@Test
	public void saveUserDataIncorrectGamesPlayed() {
		userData = new UserData(10, 10);
		userData.setGamesPlayed(-10);
		saveUserData = new SaveUserDataToXml(userData);
		assertFalse(saveUserData.saveData(true));
	}

	@Test
	public void saveUserDataIncorrectWordsPlayed() {
		userData = new UserData(-10, 10);
		userData.setGamesPlayed(10);
		saveUserData = new SaveUserDataToXml(userData);
		assertFalse(saveUserData.saveData(true));
	}

	@Test
	public void saveUserDataIncorrectLivesUsed() {
		userData = new UserData(10, -10);
		userData.setGamesPlayed(10);
		saveUserData = new SaveUserDataToXml(userData);
		assertFalse(saveUserData.saveData(true));
	}

}
