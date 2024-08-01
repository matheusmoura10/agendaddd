package com.wareline.agenda.shared.annotations.daterange;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class DateRangeValidador implements ConstraintValidator<DateRangeValidator, DateRangeDto> {
    @Override
    public boolean isValid(DateRangeDto value, ConstraintValidatorContext context) {
        return value.getStart().isBefore(value.getEnd());
    }
}