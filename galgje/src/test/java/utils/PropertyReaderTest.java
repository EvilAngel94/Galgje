package utils;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import hangman.utils.PropertyReader;

public class PropertyReaderTest {

	@Before
	public void setup() {
		PropertyReader.getInstance();
	}

	@Test
	public void getEnglishString() {
		String englishString = PropertyReader.getProperty("test", false);
		assertEquals("testInEnglish!", englishString);
	}

	@Test
	public void getDutchString() {
		String dutchString = PropertyReader.getProperty("test", true);
		assertEquals("testInNederland!", dutchString);
	}

}
