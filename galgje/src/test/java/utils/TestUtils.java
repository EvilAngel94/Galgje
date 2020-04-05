package utils;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class TestUtils {

	private static TestUtils instance;

	private TestUtils() {
		super();
	}

	public static TestUtils getInstance() {
		if (instance == null) {
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

		} catch (IOException e) {
			fail("Gegevens komen wel voor in xml bestand..");
		}

		return elementIsGevonden;
	}

	public String createTestCsvFile() {
		String nameOfFile = "test.csv";
		
		try (FileWriter csvWriter = new FileWriter(new File("./user_wordlist/" + nameOfFile))) {
			csvWriter.append("id,test\n");
			createStubMap().forEach((key, value) -> {
				try {
					csvWriter.append((key + "") + "," + value + "\n");
				} catch (IOException e) {
					fail("Something went wrong in testUtils.");
				}
			});

			csvWriter.flush();
			
		} catch (Exception ex) {
			fail("Something went wrong in testUtils.");
		}
		
		return nameOfFile;
	}
	
	public void deleteTestCsvFile() {
		new File("./user_wordlist/test.csv").deleteOnExit();		
	}

	private Map<Integer, String> createStubMap() {
		Map<Integer, String> galgjeWoorden = new HashMap<>();
		for (int i = 0; i < 60; i++) {
			galgjeWoorden.put(i, "test_" + i);
		}
		return galgjeWoorden;
	}
	
}
