package utils;

import java.util.Scanner;

public class Validator {

	private Validator() {
		super();
	}

	public static int validateUserInput(Scanner scanner, int smallestValue, int highestValue) {
		int keuze = 0;
		boolean validInput = false;

		do {
			if (scanner.hasNextInt()) { // Checks if the input is a valid integer
				keuze = scanner.nextInt();
				if (keuze < smallestValue) {
					System.out.println("Input moet groter dan " + smallestValue + " zijn");
					return validateUserInput(scanner, smallestValue, highestValue);
				}
				if (keuze > highestValue) {
					System.out.println("Input mag niet groter zijn dan " + highestValue);
					return validateUserInput(scanner, smallestValue, highestValue);
				}
				validInput = true;
			} else {
				System.out.println("Verkeerde input, kan alleen hele getallen zijn");
				scanner.next();
			}
		} while (!validInput);
		return keuze;
	}
}