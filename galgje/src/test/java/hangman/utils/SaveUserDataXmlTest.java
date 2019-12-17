package hangman.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import hangman.game.save.UserData;
import utils.TestUtils;

public class SaveUserDataXmlTest {

	private UserData userData;
	private SaveUserDataToXml saveUserData;

	@Test
	public void saveUserDataCorrect() {
		userData = new UserData(10, 10, 10);
		saveUserData = new SaveUserDataToXml(userData);
		assertTrue(saveUserData.saveData());
		assertTrue(TestUtils.getInstance().rowsFoundInFile(new File("UserData.xml"), Arrays.asList(
				"<gamesPlayed>10</gamesPlayed>", "<wordsSolved>10</wordsSolved>", "<livesUsed>10</livesUsed>")));
	}

	@Test
	public void saveUserDataIncorrectGamesPlayed() {
		userData = new UserData(-10, 10, 10);
		saveUserData = new SaveUserDataToXml(userData);
		assertFalse(saveUserData.saveData());
	}

	@Test
	public void saveUserDataIncorrectWordsPlayed() {
		userData = new UserData(10, -10, 10);
		saveUserData = new SaveUserDataToXml(userData);
		assertFalse(saveUserData.saveData());
	}

	@Test
	public void saveUserDataIncorrectLivesUsed() {
		userData = new UserData(10, 10, -10);
		saveUserData = new SaveUserDataToXml(userData);
		assertFalse(saveUserData.saveData());
	}

}
