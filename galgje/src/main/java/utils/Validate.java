package utils;

import java.util.Scanner;

public class Validate {
	
	private final Scanner scanner;
	
	public Validate(Scanner scanner) {
		super();
		this.scanner = scanner;
	}
	
	public int valideerWoordlengteKeuze() {
		int keuze = 0;
		boolean validate = false;

		do {
			if (scanner.hasNextInt()) { // Checks if the input is a valid integer
				keuze = scanner.nextInt();
				if (keuze <= 0) {
					System.out.println("Input moet groter dan 0 zijn");
					return valideerWoordlengteKeuze();
				}
				if (keuze > 2) {
					System.out.println("Input mag niet groter zijn dan 2");
					return valideerWoordlengteKeuze();
				}
				validate = true;
				return keuze;
			} else {
				System.out.println("Verkeerde input, kan alleen hele getallen zijn");
				scanner.next();
			}
		} while (validate == false);
		return keuze;
	}
	
	public int valideerKeuzeMainMenu() {
		int keuze = 0;
		boolean validate = false;

		do {
			if (scanner.hasNextInt()) { // Checks if the input is a valid integer
				keuze = scanner.nextInt();
				if (keuze <= 0) {
					System.out.println("Input moet groter dan 0 zijn");
					return valideerKeuzeMainMenu();
				}
				if (keuze > 3) {
					System.out.println("Input mag niet groter zijn dan 3");
					return valideerKeuzeMainMenu();
				}
				validate = true;
				return keuze;
			} else {
				System.out.println("Verkeerde input, kan alleen hele getallen zijn");
				scanner.next();
			}
		} while (validate == false);

		return keuze;
	}
	
	

}
