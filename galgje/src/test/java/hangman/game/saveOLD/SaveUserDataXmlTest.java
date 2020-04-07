package hangman.game.saveOLD;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hangman.game.saveOLD.SaveUserDataToXml;
import hangman.game.saveOLD.UserData;
import utils.TestUtils;

public class SaveUserDataXmlTest {

	private UserData userData;
	private SaveUserDataToXml saveUserData;

	@Before
	public void setup() throws IOException {
		deleteFileIfPresent();
		new File("userDataUnitTest.xml").createNewFile();
	}

	@After
	public void tearDown() {
		deleteFileIfPresent();
	}

	@Test
	public void saveUserGamesPlayedFoundInXML() {
		saveUserDataBase();
		assertTrue(TestUtils.getInstance().rowsFoundInFile(new File("userDataUnitTest.xml"),
				Arrays.asList("<gamesPlayed>")));
	}

	@Test
	public void saveUserWordsSolvedFoundInXML() {
		saveUserDataBase();
		assertTrue(TestUtils.getInstance().rowsFoundInFile(new File("userDataUnitTest.xml"),
				Arrays.asList("<wordsSolved>")));
	}

	@Test
	public void saveUserLivesUsedFoundInXML() {
		saveUserDataBase();
		assertTrue(TestUtils.getInstance().rowsFoundInFile(new File("userDataUnitTest.xml"),
				Arrays.asList("<livesUsed>")));
	}

	private void saveUserDataBase() {
		userData = new UserData(9, 10);
		saveUserData = new SaveUserDataToXml(userData);
		assertTrue(saveUserData.saveData(true));
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

	private void deleteFileIfPresent() {
		new File("userDataUnitTest.xml").deleteOnExit();
	}

}
