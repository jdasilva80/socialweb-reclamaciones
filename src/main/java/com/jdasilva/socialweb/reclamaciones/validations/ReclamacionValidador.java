package com.jdasilva.socialweb.reclamaciones.validations;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class ReclamacionValidador implements IReclamacionValidador {

	@Override
	public boolean supports(Class<?> clazz) {

		// return Reclamacion.class.isAssignableFrom(clazz) ||
		// Usuario.class.equals(clazz) || Mensaje.class.equals(clazz);
		return Object.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		// Reclamacion reclamacion = (Reclamacion) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "requerido.reclamacion.username");
//
//		if (!reclamacion.getPeticion().matches("[0-9]{3}[.,][\\d]{3}[.,][aA-zZ]{1}")) {
//
//			errors.rejectValue("peticion", "pattern.reclamacion.peticion");
//		}

	}

}
