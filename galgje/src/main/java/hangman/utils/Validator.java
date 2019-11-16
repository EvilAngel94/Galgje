package hangman.utils;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Validator {
	
	private static final Logger LOGGER = LogManager.getLogger(Validator.class);

	private Validator() {
		super();
	}

	public static int validateUserInput(Scanner scanner, int smallestValue, int highestValue) {
		LOGGER.info("Validating the following input: SCANNER OBJECT.. with the following conditions: smallest {}, highestValue {}", smallestValue, highestValue);
		int keuze = 0;
		boolean validInput = false;

		do {
			if (scanner.hasNextInt()) { // Checks if the input is a valid integer
				keuze = scanner.nextInt();
				
				if (keuze < smallestValue) {
					LOGGER.info("Input is smaller than smallest value. Input should be bigger than {}", smallestValue);
					System.out.println("Input moet groter dan " + smallestValue + " zijn");
					return validateUserInput(scanner, smallestValue, highestValue);
				}
				
				if (keuze > highestValue) {
					LOGGER.info("Input is greater than the greatest value. Input should be smaller than {}", highestValue);
					System.out.println("Input mag niet groter zijn dan " + highestValue);
					return validateUserInput(scanner, smallestValue, highestValue);
				}
				
				validInput = true;
			} else {
				LOGGER.info("Wrong input, can only play with whole numbers. Decimals and letters are not recognised.");
				System.out.println("Verkeerde input, kan alleen hele getallen zijn");
				scanner.next();
			}
		} while (!validInput);
		return keuze;
	}
}