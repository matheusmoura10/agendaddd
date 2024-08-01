package com.wareline.agenda.domain.paciente;

import java.util.Set;

import com.wareline.agenda.shared.validation.Error;
import com.wareline.agenda.shared.validation.ValidationHandlerInterface;
import com.wareline.agenda.shared.validation.Validator;
import com.wareline.agenda.shared.validation.validators.AddressValidator;
import com.wareline.agenda.shared.vo.AddressVO;

public class PacientValidator extends Validator {

    private static final int NAME_MAX_LENGTH = 100;
    private static final int NAME_MIN_LENGTH = 3;
    private static final int PHONE_MAX_LENGTH = 15;
    private static final int PHONE_MIN_LENGTH = 8;
    private static final int EMAIL_MAX_LENGTH = 100;
    private static final int EMAIL_MIN_LENGTH = 3;
    private static final int MAX_ADDRESSES = 5;

    private static final String ERROR_NAME_EMPTY = "Nome do paciente não pode ser vazio";
    private static final String ERROR_NAME_TOO_LONG = "Nome do paciente não pode ter mais de " + NAME_MAX_LENGTH
            + " caracteres";
    private static final String ERROR_NAME_TOO_SHORT = "Nome do paciente não pode ter menos de " + NAME_MIN_LENGTH
            + " caracteres";
    private static final String ERROR_NAME_INVALID = "Nome do paciente não pode conter números";

    private static final String ERROR_PHONE_EMPTY = "Telefone do paciente não pode ser vazio";
    private static final String ERROR_PHONE_TOO_LONG = "Telefone do paciente não pode ter mais de " + PHONE_MAX_LENGTH
            + " caracteres";
    private static final String ERROR_PHONE_TOO_SHORT = "Telefone do paciente não pode ter menos de " + PHONE_MIN_LENGTH
            + " caracteres";
    private static final String ERROR_PHONE_INVALID = "Telefone do paciente não pode conter letras";

    private static final String ERROR_EMAIL_EMPTY = "Email do paciente não pode ser vazio";
    private static final String ERROR_EMAIL_TOO_LONG = "Email do paciente não pode ter mais de " + EMAIL_MAX_LENGTH
            + " caracteres";
    private static final String ERROR_EMAIL_TOO_SHORT = "Email do paciente não pode ter menos de " + EMAIL_MIN_LENGTH
            + " caracteres";
    private static final String ERROR_EMAIL_INVALID = "Email do paciente inválido";

    private static final String ERROR_ADDRESS_EMPTY = "Endereços do paciente não podem ser vazios";
    private static final String ERROR_TOO_MANY_ADDRESSES = "Paciente não pode ter mais de " + MAX_ADDRESSES
            + " endereços";

    private final PacientEntity pacientEntity;

    public PacientValidator(final PacientEntity aPacienteEntity, final ValidationHandlerInterface aHandler) {
        super(aHandler);
        this.pacientEntity = aPacienteEntity;
    }

    @Override
    public void validate() {
        validateName();
        validatePhone();
        validateEmail();
        validateEnderecos();
    }

    private void validateName() {
        String nome = pacientEntity.getName();
        if (isNullOrEmpty(nome)) {
            validationHandler().append(new Error("name", ERROR_NAME_EMPTY));
        } else {
            if (nome.length() > NAME_MAX_LENGTH) {
                validationHandler().append(new Error("name", ERROR_NAME_TOO_LONG));
            }
            if (nome.length() < NAME_MIN_LENGTH) {
                validationHandler().append(new Error("name", ERROR_NAME_TOO_SHORT));
            }
            if (nome.matches(".*\\d.*")) {
                validationHandler().append(new Error("name", ERROR_NAME_INVALID));
            }
        }
    }

    private void validatePhone() {
        String telefone = pacientEntity.getPhone();
        if (isNullOrEmpty(telefone)) {
            validationHandler().append(new Error("phone", ERROR_PHONE_EMPTY));
        } else {
            if (telefone.length() > PHONE_MAX_LENGTH) {
                validationHandler().append(new Error("phone", ERROR_PHONE_TOO_LONG));
            }
            if (telefone.length() < PHONE_MIN_LENGTH) {
                validationHandler().append(new Error("phone", ERROR_PHONE_TOO_SHORT));
            }
            if (telefone.matches(".*\\D.*")) {
                validationHandler().append(new Error("phone", ERROR_PHONE_INVALID));
            }
        }
    }

    private void validateEmail() {
        String email = pacientEntity.getEmail();
        if (isNullOrEmpty(email)) {
            validationHandler().append(new Error("email", ERROR_EMAIL_EMPTY));
        } else {
            if (email.length() > EMAIL_MAX_LENGTH) {
                validationHandler().append(new Error("email", ERROR_EMAIL_TOO_LONG));
            }
            if (email.length() < EMAIL_MIN_LENGTH) {
                validationHandler().append(new Error("email", ERROR_EMAIL_TOO_SHORT));
            }
            if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                validationHandler().append(new Error("email", ERROR_EMAIL_INVALID));
            }
        }
    }

    private void validateEnderecos() {
        Set<AddressVO> enderecos = pacientEntity.getAddress();
        if (enderecos == null || enderecos.isEmpty()) {
            validationHandler().append(new Error("address", ERROR_ADDRESS_EMPTY));
        } else {
            if (enderecos.size() > MAX_ADDRESSES) {
                validationHandler().append(new Error("address", ERROR_TOO_MANY_ADDRESSES));
            }
            for (AddressVO endereco : enderecos) {
                new AddressValidator(endereco, validationHandler()).validate();
            }
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}