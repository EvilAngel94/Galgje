package hangman.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyReader {

	private static final Logger LOGGER = LogManager.getLogger(PropertyReader.class);

	private static PropertyReader instance = null;
	private static Properties properties = null;

	private PropertyReader() {
		super();
	}

	public static PropertyReader getInstance() {
		if (instance == null) {
			LOGGER.debug("Instance of PropertyReader is made.");

			instance = new PropertyReader();
			try (FileReader reader = new FileReader("Application.properties")) {
				properties = new Properties();
				properties.load(reader);

			} catch (FileNotFoundException e) {
				LOGGER.error("File is not found!. Path: {}", e);
			} catch (IOException e) {
				LOGGER.error("IOException.. Stacktrace: {}", e);
			}
		}
		return instance;
	}

	public static String getProperty(String name, boolean isDutch) {
		return isDutch ? properties.getProperty(name) : properties.getProperty("en." + name);
	}
	
	public String getProperty(String name) {
		return properties.getProperty(name);
	}
}
