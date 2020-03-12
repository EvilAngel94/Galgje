package hangman.game.wordlist;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

public class AddWordToWordListTest {

	@Test
	public void addNewWordToCsvFile() throws IOException {
		AddWordToWordList addWordToList = new AddWordToWordList();
		addWordToList.addNewWordToCsvList("4_words_english.csv", "TestTest");
		
		Map<Integer, String> galgjeWoorden = new ReadWordList().readUserDefinedWordList("4_words_english.csv");

		assertEquals(61, galgjeWoorden.size());
	}

	
}
