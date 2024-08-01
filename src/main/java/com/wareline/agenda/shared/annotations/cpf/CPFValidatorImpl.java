package com.wareline.agenda.shared.annotations.cpf;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidatorImpl implements ConstraintValidator<CPFValidator, String> {

    @Override
    public void initialize(CPFValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.isEmpty()) {
            return false;
        }

        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11) {
            return false;
        }

        if (isAllDigitsEqual(cpf)) {
            return false;
        }

        return isCPFValid(cpf);
    }

    private boolean isAllDigitsEqual(String cpf) {
        char firstDigit = cpf.charAt(0);
        return cpf.chars().allMatch(c -> c == firstDigit);
    }

    private boolean isCPFValid(String cpf) {
        int sum;
        int rest;
        String digit;
        int[] weights;

        sum = 0;
        weights = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2 };
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * weights[i];
        }
        rest = 11 - (sum % 11);
        digit = rest > 9 ? "0" : Integer.toString(rest);
        if (!digit.equals(Character.toString(cpf.charAt(9)))) {
            return false;
        }

        sum = 0;
        weights = new int[] { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * weights[i];
        }
        rest = 11 - (sum % 11);
        digit = rest > 9 ? "0" : Integer.toString(rest);
        return digit.equals(Character.toString(cpf.charAt(10)));
    }
}