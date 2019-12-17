package hangman.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Test;

import hangman.game.save.UserData;

public class ReadUserDataToXmlTest {
	
	@After
	public void teardown() {
		new File("userDataUnitTest.xml").deleteOnExit();
	}

	@Test
	public void readDataCorrect() {
		UserData userData = new UserData(8, 9, 10);
		SaveUserDataToXml saveUserData = new SaveUserDataToXml(userData);
		assertTrue(saveUserData.saveData(true));
		
		UserData userdata = ReadUserDataToXml.readData(true);
		assertEquals(8, userdata.getGamesPlayed());
		assertEquals(9, userdata.getWordsSolved());
		assertEquals(10, userdata.getLivesUsed());
	}

}
