package hangman.game.saveOLD;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is responsible for reading the xml file and put the values found
 * to the correct fields of the {@link UserData}
 */
public class ReadUserDataToXml {

	private static final Logger LOGGER = LogManager.getLogger(ReadUserDataToXml.class);

	private ReadUserDataToXml() {
		super();
	}

	/**
	 * This method is responsible for reading the data from the XML file. 
	 * 
	 * @param unittest 
	 * @return The {@link UserData} retreived form the file.
	 */
	public static UserData readData(boolean unittest) {
		File xmlFile;

		if (unittest) {
			LOGGER.debug("Unittest is active..");
			xmlFile = new File("userDataUnitTest.xml");
		} else {
			LOGGER.debug("Reading data from userData.xml..");
			xmlFile = new File("userData.xml");
		}

		JAXBContext jaxbContext;
		UserData userDataFromFile = null;

		try {
			
			jaxbContext = JAXBContext.newInstance(UserData.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			userDataFromFile = (UserData) jaxbUnmarshaller.unmarshal(xmlFile);

		} catch (JAXBException ex) {
			LOGGER.debug("Error reading xml file of Userdata. {}", ex);
			LOGGER.debug(
					"Warning!! Retreiving the user information form the file is null. Therefore a new instance is created with 0 values..");
			userDataFromFile = new UserData(0, 0);
			userDataFromFile.setGamesPlayed(0);
		}

		LOGGER.debug("Succesfull reading data from xml file.");
		return userDataFromFile;
	}

}
