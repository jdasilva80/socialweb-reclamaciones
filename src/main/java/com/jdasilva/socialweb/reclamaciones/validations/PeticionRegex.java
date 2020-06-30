package com.jdasilva.socialweb.reclamaciones.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PeticionRegexValidador.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface PeticionRegex {

	String message() default "el formato de la petición no es correcto";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
