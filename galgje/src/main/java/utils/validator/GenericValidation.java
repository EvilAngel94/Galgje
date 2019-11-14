package utils.validator;

import java.util.function.Predicate;

public class GenericValidation<K> implements Validation<K> {

	private Predicate<K> predicate;
	
	private GenericValidation(Predicate<K> predicate) {
		this.predicate = predicate;
	}

	/**
	 * This method takes in a predicate (or a condition)
	 * 
	 * @param <K>
	 * @param predicate
	 * 
	 * @return a GenericValidation
	 */
	public static <K> GenericValidation<K> from(Predicate<K> predicate) {
		return new GenericValidation<>(predicate);
	}

	@Override
	public GenericValidationResult valid(K valueToBeTestedAgainst) {
		return predicate.test(valueToBeTestedAgainst) ? GenericValidationResult.pass() : GenericValidationResult.fail();
	}
}
