package utils;

import java.util.Scanner;

public class Validator {

	private final Scanner scanner;

	public Validator(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	public int validateUserInput(int smallestValue, int highestValue) {
		int keuze = 0;
		boolean validInput = false;

		do {
			if (scanner.hasNextInt()) { // Checks if the input is a valid integer
				keuze = scanner.nextInt();
				if (keuze < smallestValue) {
					System.out.println("Input moet groter dan " + smallestValue + " zijn");
					return validateUserInput(smallestValue, highestValue);
				}
				if (keuze > highestValue) {
					System.out.println("Input mag niet groter zijn dan " + highestValue);
					return validateUserInput(smallestValue, highestValue);
				}
				validInput = true;
				return keuze;
			} else {
				System.out.println("Verkeerde input, kan alleen hele getallen zijn");
				scanner.next();
			}
		} while (!validInput);
		return keuze;
	}
}