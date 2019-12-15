package hangman.game.save;

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

}
