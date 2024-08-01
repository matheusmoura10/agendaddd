package com.wareline.agenda.shared.annotations.hourrange;

import java.text.SimpleDateFormat;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HourRangeImpl implements ConstraintValidator<HourRange, HourRangeDto> {
    @Override
    public boolean isValid(HourRangeDto value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        String start = value.getStart().toString();
        String end = value.getEnd().toString();

        // regex para validar o formato HH:mm
        var regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

        if (!start.matches(regex) || !end.matches(regex)) {
            return false;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        try {
            // is hour
            if (Integer.parseInt(start.substring(0, 2)) > 23) {
                return false;
            }

            // is hour
            if (Integer.parseInt(end.substring(0, 2)) > 23) {
                return false;
            }

            return formatter.parse(start).before(formatter.parse(end));
        } catch (Exception e) {
            return false;
        }
    }
}