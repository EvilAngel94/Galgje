package hangman.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import hangman.game.save.UserData;

public class ReadUserDataToXmlTest {

	@Test
	public void readDataCorrect() {
		UserData userdata = ReadUserDataToXml.readData(true);
		assertEquals(8, userdata.getGamesPlayed());
		assertEquals(9, userdata.getWordsSolved());
		assertEquals(10, userdata.getLivesUsed());
	}

}
