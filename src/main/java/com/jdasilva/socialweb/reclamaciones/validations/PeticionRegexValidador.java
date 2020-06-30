package com.jdasilva.socialweb.reclamaciones.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeticionRegexValidador implements ConstraintValidator<PeticionRegex, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value.matches("[0-9]{3}[.,][\\d]{3}[.,][aA-zZ]{1}")) {

			return true;
		}

		return false;
	}

}
