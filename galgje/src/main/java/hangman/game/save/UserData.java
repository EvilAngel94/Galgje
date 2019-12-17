package hangman.game.save;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import hangman.utils.SaveUserDataToXml;

/**
 * This class is the representation of the user data. Each of the elements are
 * saved within the xml file.
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserData {

	private int gamesPlayed;
	private int wordsSolved;
	private int livesUsed;

	public UserData(int gamePlayed, int wordsSolved, int livesUsed) {
		super();
		this.gamesPlayed = gamePlayed;
		this.wordsSolved = wordsSolved;
		this.livesUsed = livesUsed;
	}

	/**
	 * Needed for the XML save class {@link SaveUserDataToXml}
	 */
	public UserData() {
		super();
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public int getWordsSolved() {
		return wordsSolved;
	}

	public void setWordsSolved(int wordsSolved) {
		this.wordsSolved = wordsSolved;
	}

	public int getLivesUsed() {
		return livesUsed;
	}

	public void setLivesUsed(int livesUsed) {
		this.livesUsed = livesUsed;
	}
	
	@Override
	public String toString() {
		return "UserData [gamesPlayed=" + gamesPlayed + ", wordsSolved=" + wordsSolved + ", livesUsed=" + livesUsed
				+ "]";
	}

	public boolean isValid() {
		if(gamesPlayed < 0) {
			return false;
		}
		
		if(wordsSolved < 0) {
			return false;
		}
		
		if(livesUsed < 0) {
			return false;
		}
		
		return true;
	}

}
