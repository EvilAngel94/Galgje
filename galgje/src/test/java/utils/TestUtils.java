package utils;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class TestUtils {
	
	private static TestUtils instance;
	
	private TestUtils() {
		super();
	}
	
	public static TestUtils getInstance() {
		if(instance == null) {
			instance = new TestUtils();
		}
		return instance;
	}

	public boolean rowsFoundInFile(File userDataXml, List<String> gegevensDieMoetenVoorkomen) {
		boolean elementIsGevonden = false;

		try {

			String xmlBestandVanUserData = FileUtils.readFileToString(userDataXml, StandardCharsets.UTF_8);
			// "brief::contains" is hetzelfde als "gegevens -> brief.contains(gegevens));"
			elementIsGevonden = gegevensDieMoetenVoorkomen.stream().anyMatch(xmlBestandVanUserData::contains);

		} catch (@SuppressWarnings("unused") IOException e) {
			fail("Gegevens komen wel voor in xml bestand..");
		}

		return elementIsGevonden;
	}
	
}
