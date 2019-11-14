package utils.validator;

/**
 * This interface is a functional interface. Meaning methods are declared here which can be used by their implementation.
 * Furthermore this is an generic validation framework.
 * 
 * @author PolarBear Dev
 * @param <K> -> object which will be validated
 */
@FunctionalInterface
public interface Validation<K> {

	public GenericValidationResult valid(K param);

	/**
	 * This default method is to chain another validation to an already existing method
	 * 
	 * @return
	 */
	default Validation<K> and(Validation<K> other) {
		return (param) -> {
			GenericValidationResult result = this.valid(param);
			return result.isValid() ? result : other.valid(param);
		};
	}

	default Validation<K> or(Validation<K> other) {
		return (param) -> {
			GenericValidationResult result = this.valid(param);
			return result.isValid() ? result : other.valid(param);
		};
	}
}
