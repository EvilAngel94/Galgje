package utils;

import static org.junit.Assert.*;

import org.junit.Test;

import hangman.utils.Validator;

public class ValidatorTest {

	@Test
	public void isNummericFalse() {
		assertFalse(Validator.isNummeric("A"));
	}

	@Test
	public void isNummericTrue() {
		assertTrue(Validator.isNummeric("1"));
	}

	@Test
	public void isNummericFalseMultipleCharacters() {
		assertFalse(Validator.isNummeric("1234A"));
	}

	@Test
	public void greaterThanHighestValueFalse() {
		assertFalse(Validator.inputIsGreaterThanHighestValue("1", 4));
	}

	@Test
	public void greaterThanHighestValueTrue() {
		assertTrue(Validator.inputIsGreaterThanHighestValue("8", 3));
	}
	
	@Test(expected = NumberFormatException.class)
	public void greaterThanHighestValueWrongInput() {
		assertFalse(Validator.inputIsGreaterThanHighestValue("abc", 3));
	}
	
	@Test
	public void smallerThanHighestValueFalse() {
		assertFalse(Validator.inputIsSmallerThanSmallestValue("3", 2));
	}

	@Test
	public void smallerThanHighestValueTrue() {
		assertTrue(Validator.inputIsSmallerThanSmallestValue("-12", 3));
	}
	
	@Test(expected = NumberFormatException.class)
	public void smallerThanHighestValueWrongInput() {
		assertFalse(Validator.inputIsSmallerThanSmallestValue("abc", 3));
	}
}
