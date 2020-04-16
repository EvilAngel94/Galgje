package hangman.utils;

/**
 * This class is responsible for all the validations of the project. 
 * Within this class only a 'generic' method should be made.
 * 
 * If an error should be displayed, these should be stated when the validator is called.
 * 
 * @author PolarBear Dev
 *
 */
public class Validator {

	private static final String NUMMERIC_REGEX = "^[0-9]*$";

	private Validator() {
		super();
	}

	public static boolean isNummeric(String input) {
		// would return null if the input does not match the regular expression.
		if(input.matches(NUMMERIC_REGEX)) {
			return true;
		}
		return false;
	}
	
	public static boolean inputIsSmallerThanSmallestValue(String input, int smallestValue) {
		if(input == null || input.equals("")) {
			return true;
		}
		return Integer.parseInt(input) < smallestValue;
	}

	public static boolean inputIsGreaterThanHighestValue(String input, int highestValue) {
		if(input == null || input.equals("")) {
			return true;
		}
		return Integer.parseInt(input) > highestValue;
	}
	
	public static boolean isAlpabetic(String input) {
		return input.chars().allMatch(Character::isLetter);
	}
}