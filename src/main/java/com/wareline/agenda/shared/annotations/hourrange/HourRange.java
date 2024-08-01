package com.wareline.agenda.shared.annotations.hourrange;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = HourRangeImpl.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HourRange {
    String message() default "Hour init must be before hour end";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}