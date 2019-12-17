package hangman.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hangman.game.save.UserData;

public class SaveUserDataToXml {
	private static final Logger LOGGER = LogManager.getLogger(SaveUserDataToXml.class);

	private UserData userData;

	public SaveUserDataToXml(UserData userData) {
		super();
		this.userData = userData;
	}

	/**
	 * Saves the userdata in an xml file.
	 * 
	 * @return true if succesfull otherwise false.
	 */
	public boolean saveData(boolean unittest) {

		if (!userData.isValid()) {
			LOGGER.debug("UserData does not have valid values! {}", userData.toString());
			return false;
		}

		UserData oldData = ReadUserDataToXml.readData(unittest);
		userData = addOldAndNewData(userData, oldData);

		try {
			// Create JAXB Context
			JAXBContext jaxbContext = JAXBContext.newInstance(UserData.class);

			// Create Marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// Required formatting??
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File file;
			// Store XML to File
			if (unittest) {
				file = new File("userDataUnitTest.xml");
			} else {
				file = new File("userData.xml");
			}

			// Writes XML file to file-system
			jaxbMarshaller.marshal(userData, file);
			return true;

		} catch (JAXBException ex) {
			LOGGER.debug("Error trying to save the userData: {}", ex);
			return false;
		}

	}

	private UserData addOldAndNewData(UserData userData, UserData oldData) {

		userData.setGamesPlayed(userData.getGamesPlayed() + oldData.getGamesPlayed());
		userData.setLivesUsed(userData.getLivesUsed() + oldData.getLivesUsed());
		userData.setWordsSolved(userData.getWordsSolved() + oldData.getWordsSolved());
		return userData;
	}
}
