package com.jdasilva.socialweb.reclamaciones.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class RequeridoValidador implements ConstraintValidator<Requerido, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value != null && StringUtils.hasText(value)) {

			return true;
		}

		return false;
	}

}
