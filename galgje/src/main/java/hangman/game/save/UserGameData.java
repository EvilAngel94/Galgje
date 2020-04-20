package hangman.game.save;

public class UserGameData {

	private int gamesPlayed;
	private int wordsSolved;
	private int livesUsed;

	public UserGameData(int wordsSolved, int livesUsed) {
		super();
		this.wordsSolved = wordsSolved;
		this.livesUsed = livesUsed;
	}
	
	public UserGameData() {
		this(0, 0);
		this.gamesPlayed = 0;
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
		return "\nGames played = " + gamesPlayed + "\nWords solved = " + wordsSolved
				+ "\nAmount of lives used = " + livesUsed + "\n";
	}

	/**
	 * Deze methode moet alles controleren, anders kan het voorkomen dat niet alles goed gecontroleerd wordt.
	 * @return 	
	 * 			true -> wanneer alle waardes goed zijn
	 * 			false -> wanneer een waarde lager is dan 0
	 */
	public boolean isValid() {
		if (gamesPlayed < 0) {
			return false;
		}
		if (wordsSolved < 0) {
			return false;
		}
		if (livesUsed < 0) {
			return false;
		}
		return true;
	}
}
