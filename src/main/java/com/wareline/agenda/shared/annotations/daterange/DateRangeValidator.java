package com.wareline.agenda.shared.annotations.daterange;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = DateRangeValidador.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRangeValidator {

    String message() default "Init date must be before end date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}