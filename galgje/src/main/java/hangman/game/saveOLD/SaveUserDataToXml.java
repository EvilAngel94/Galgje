package hangman.game.saveOLD;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is responsible for saving the game data after a played game.
 *
 */
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
			LOGGER.debug("UserData does not have valid values! {}", userData);
			return false;
		}

		UserData oldData = ReadUserDataToXml.readData(unittest);
		userData = addOldAndNewData(userData, oldData);

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(UserData.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File file;

			if (unittest) {
				file = new File("userDataUnitTest.xml");
			} else {
				file = new File("userData.xml");
			}

			// Write the object to the file.
			jaxbMarshaller.marshal(userData, file);
			return true;
			
		} catch (JAXBException ex) {
			LOGGER.debug("Error trying to save the userData: {}", ex);
			return false;
		}

	}

	private UserData addOldAndNewData(UserData userData, UserData oldData) {
		userData.setGamesPlayed(oldData.getGamesPlayed() + 1);
		userData.setLivesUsed(userData.getLivesUsed() + oldData.getLivesUsed());
		userData.setWordsSolved(userData.getWordsSolved() + oldData.getWordsSolved());

		return userData;
	}
}
