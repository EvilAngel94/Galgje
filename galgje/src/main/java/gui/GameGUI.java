package gui;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the main game GUI. 
 * 
 */
public class GameGUI {

	private Scanner scanner;
	private Map<Integer, String> galgjeWoorden;
	
	public GameGUI(Scanner scanner, Map<Integer, String> galgjeWoorden) {
		super();
		this.scanner = scanner;
		this.galgjeWoorden = galgjeWoorden;
	}

	/**
	 * This is the main game which will be played when choosing the game option.
	 */
	public void gamePlay() {
		int life = 5;
		int numberOfGuesses = 0;
		String woord = chooseRandomWord();
		

		char[] filler = new char[woord.length()];
		int i = 0;
		while (i < woord.length()) {
			filler[i] = '-';
			if (woord.charAt(i) == ' ') {
				filler[i] = ' ';
			}
			i++;
		}

		System.out.print(filler);
		System.out.println("    life remaining = " + life);
		ArrayList<Character> list = new ArrayList<Character>();
		
		while (life > 0) { // gaat door zolang het kan.
			char x = scanner.next().charAt(0);

			if (list.contains(x)) {
				System.out.println("You've already enterd: " + x);
				continue;
			}
			
			list.add(x);
			if (woord.contains(x + "")) {
				for (int y = 0; y < woord.length(); y++) {
					if (woord.charAt(y) == x) { // checks the character and will replace '-' by the character.
						filler[y] = x;
					}
				}
			} else {
				life--; // Gaat leven eraf
			}
			if (woord.equals(String.valueOf(filler))) {
				System.out.println(filler);
				System.out.println("You won!");
				break;
			}

			System.out.print(filler);
			System.out.println("    life remaining = " + life);
		}

		if (life == 0) {
			System.out.println(woord);
			System.out.println("You lost");
		}
	}
	
	/**
	 * Select a random word from the HashMap. 
	 *  
	 * @return the word which is selected.
	 */
	private String chooseRandomWord() {
		int keyValue = selectRandomKey();
		String randomSelectedWord = galgjeWoorden.get(keyValue);
		
		return randomSelectedWord;
	}
	
	/**
	 * This method generates a random number.
	 * The number which is randomly generated is used to pick the corresponding word.
	 * 
	 * @return keyValue
	 */
	private int selectRandomKey() {
		Random random = new Random();
		int maxValue = galgjeWoorden.size();
		int keyValue = random.nextInt((maxValue+1 -1) +1) +1;
		return keyValue;
	}
}
