package com.wareline.agenda.shared.validation.validators;

import com.wareline.agenda.shared.validation.Error;
import com.wareline.agenda.shared.validation.ValidationHandlerInterface;
import com.wareline.agenda.shared.vo.AddressVO;

public class AddressValidator {

    private static final int STREET_MAX_LENGTH = 100;
    private static final int STREET_MIN_LENGTH = 3;
    private static final int NUMBER_MAX_LENGTH = 10;
    private static final int NUMBER_MIN_LENGTH = 1;
    private static final int COMPLEMENT_MAX_LENGTH = 100;
    private static final int NEIGHBORHOOD_MAX_LENGTH = 100;
    private static final int NEIGHBORHOOD_MIN_LENGTH = 3;
    private static final int CITY_MAX_LENGTH = 100;
    private static final int CITY_MIN_LENGTH = 3;
    private static final int STATE_LENGTH = 2;
    private static final int ZIP_CODE_LENGTH = 9;

    private static final String ERROR_EMPTY = "%s cannot be empty";
    private static final String ERROR_MIN_LENGTH = "%s cannot be less than %d characters";
    private static final String ERROR_MAX_LENGTH = "%s cannot be more than %d characters";
    private static final String ERROR_INVALID_ZIP_CODE = "Invalid address ZIP code";
    private static final String ERROR_INVALID_ZIP_CODE_LENGTH = "Address ZIP code must be " + ZIP_CODE_LENGTH
            + " characters long";

    private final AddressVO address;
    private final ValidationHandlerInterface validationHandler;

    public AddressValidator(final AddressVO address, final ValidationHandlerInterface validationHandler) {
        this.address = address;
        this.validationHandler = validationHandler;
    }

    public void validate() {
        validateFieldLength(address.getStreet(), "Street", STREET_MAX_LENGTH, STREET_MIN_LENGTH);
        validateFieldLength(address.getNumber(), "Number", NUMBER_MAX_LENGTH, NUMBER_MIN_LENGTH);
        validateOptionalFieldLength(address.getComplement(), "Complement", COMPLEMENT_MAX_LENGTH);
        validateFieldLength(address.getNeighborhood(), "Neighborhood", NEIGHBORHOOD_MAX_LENGTH,
                NEIGHBORHOOD_MIN_LENGTH);
        validateFieldLength(address.getCity(), "City", CITY_MAX_LENGTH, CITY_MIN_LENGTH);
        validateExactFieldLength(address.getState(), "State", STATE_LENGTH);
        validateZipCode(address.getZipCode());
    }

    private void validateFieldLength(String value, String fieldName, int maxLength, int minLength) {
        if (isNullOrEmpty(value)) {
            validationHandler.append(new Error(fieldName, String.format(ERROR_EMPTY, fieldName)));
        } else {
            if (value.length() > maxLength) {
                validationHandler.append(new Error(fieldName, String.format(ERROR_MAX_LENGTH, fieldName, maxLength)));
            }
            if (value.length() < minLength) {
                validationHandler.append(new Error(fieldName, String.format(ERROR_MIN_LENGTH, fieldName, minLength)));
            }
        }
    }

    private void validateOptionalFieldLength(String value, String fieldName, int maxLength) {
        if (value != null && value.length() > maxLength) {
            validationHandler.append(new Error(fieldName, String.format(ERROR_MAX_LENGTH, fieldName, maxLength)));
        }
    }

    private void validateExactFieldLength(String value, String fieldName, int length) {
        if (isNullOrEmpty(value)) {
            validationHandler.append(new Error(fieldName, String.format(ERROR_EMPTY, fieldName)));
        } else if (value.length() != length) {
            validationHandler.append(new Error(fieldName, String.format(ERROR_MIN_LENGTH, fieldName, length)));
        }
    }

    private void validateZipCode(String zipCode) {
        if (isNullOrEmpty(zipCode)) {
            validationHandler.append(new Error("ZIP Code", String.format(ERROR_EMPTY, "ZIP Code")));
        } else {
            if (zipCode.length() != ZIP_CODE_LENGTH) {
                validationHandler.append(new Error("ZIP Code", ERROR_INVALID_ZIP_CODE_LENGTH));
            } else if (!zipCode.matches("\\d{5}-\\d{3}")) {
                validationHandler.append(new Error("ZIP Code", ERROR_INVALID_ZIP_CODE));
            }
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}