package hangman.game.wordlist;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class DeleteWordFromWordlistTest {
	
	@Before
	public void setup() {
		
	}

	@Test
	public void deleteWordFormFile() throws IOException {
		
		DeleteWordFromWordList.deleteWordFromCsvList("4_words_english.csv", "TestTest");
		
		Map<Integer, String> galgjeWoorden = new ReadWordList().readUserDefinedWordList("4_words_english.csv");

		assertEquals(60, galgjeWoorden.size());
	}

}
