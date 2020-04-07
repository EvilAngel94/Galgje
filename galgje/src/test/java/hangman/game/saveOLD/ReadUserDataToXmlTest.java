package hangman.game.saveOLD;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hangman.game.saveOLD.ReadUserDataToXml;
import hangman.game.saveOLD.SaveUserDataToXml;
import hangman.game.saveOLD.UserData;

public class ReadUserDataToXmlTest {

	@Before
	public void setup() throws IOException {
		deleteFileIfPresent();
		new File("userDataUnitTest.xml").createNewFile();
	}

	@After
	public void teardown() {
		deleteFileIfPresent();
	}

	@Test
	public void readDataCorrect() {
		UserData userData = new UserData(9, 10);
		SaveUserDataToXml saveUserData = new SaveUserDataToXml(userData);
		assertTrue(saveUserData.saveData(true));

		UserData userdata = ReadUserDataToXml.readData(true);
		assertEquals(1, userdata.getGamesPlayed());
		assertEquals(9, userdata.getWordsSolved());
		assertEquals(10, userdata.getLivesUsed());
	}

	private void deleteFileIfPresent() {
		new File("userDataUnitTest.xml").deleteOnExit();
	}
}
