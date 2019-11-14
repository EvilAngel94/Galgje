package utils.validator;

import java.util.Optional;

public class GenericValidationResult {

	private boolean valid;

	private GenericValidationResult(boolean valid) {
		this.valid = valid;
	}
	
	public boolean isValid() {
		return valid;
	}

	public static GenericValidationResult pass() {
		return new GenericValidationResult(true);
	}

	public static GenericValidationResult fail() {
		return new GenericValidationResult(false);
	}

	public Optional<String> getErrorMessageIfInvalid(String field) {
		return this.valid ? Optional.empty() : Optional.of(field);
	}

}