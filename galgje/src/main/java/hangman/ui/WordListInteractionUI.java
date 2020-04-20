package hangman.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hangman.game.wordlist.WordlistInteractions;
import hangman.utils.PropertyReader;
import hangman.utils.Validator;

public class WordListInteractionUI {

	private static final Logger LOGGER = LogManager.getLogger(WordListInteractionUI.class);

	private final Scanner scanner;
	private boolean isDutch;
	
	private Scanner scannerForAddingWords = new Scanner(System.in);

	public WordListInteractionUI(Scanner scanner, boolean isDutch) {
		super();
		this.scanner = scanner;
		this.isDutch = isDutch;
	}

	public void selectOptionToBePreformed() {
		System.out.println("Kies een optie die je wilt doen: [1] woord toevoegen, [2] woord verwijderen");
		String input = scanner.next();

		if (!inputIsValid(input)) {
			selectOptionToBePreformed();
		}

		int choice = Integer.parseInt(input);

		if (choice == 1) {
			System.out.println("Voeg een woord toe:");
			

			String wordToAdd = scannerForAddingWords.nextLine();

			if (isWordValid(wordToAdd)) {
				System.out.println("Word is valid and will be saved in all lowercase characters.");
				System.out.println("Word to add is valid " + wordToAdd.toLowerCase());
				
				WordlistInteractions.addNewWordToList("4_words_dutch.csv", wordToAdd.toLowerCase());

			} else {
				System.out.println("Word to add is not valid " + wordToAdd);
				selectOptionToBePreformed();
			}
		}
		if(choice == 2) {
			System.out.println("Verwijder een woord:");
			
			String wordToDelete = scannerForAddingWords.nextLine();
			if(isWordValid(wordToDelete)) {
				System.out.println("Word is valid and will be saved in all lowercase characters.");
				System.out.println("Word to add is valid " + wordToDelete.toLowerCase());
				
				WordlistInteractions.deleteWordFromUserDefinedWordlist("4_words_dutch.csv", wordToDelete.toLowerCase());
			} else {
				System.out.println("Word to delete is not recognised or valid " + wordToDelete);
				selectOptionToBePreformed();
			}
			
		}
		System.out.println("End of loop");
	}

	private boolean isWordValid(String wordToAdd) {
		return Validator.isAlpabetic(wordToAdd);
	}

	private boolean inputIsValid(String input) {
		if (!Validator.isNummeric(input)) {
			System.out.println(PropertyReader.getProperty("validation.input.invalid", isDutch));
			return false;
		}

		if (Validator.inputIsSmallerThanSmallestValue(input, 1)) {
			LOGGER.debug("Input is smaller than smallest value. Input should be bigger than {}", 1);
			System.out.println(PropertyReader.getProperty("validation.input.toosmall", isDutch));
			return false;
		}

		if (Validator.inputIsGreaterThanHighestValue(input, 10)) {
			LOGGER.debug("Input is greater than the higest value. Input should be smaller than {}", 10);
			System.out.println(PropertyReader.getProperty("validation.input.toobig", isDutch));
			return false;
		}
		return true;
	}

}
