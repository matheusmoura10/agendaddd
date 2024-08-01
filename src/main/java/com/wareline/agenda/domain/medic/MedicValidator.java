package com.wareline.agenda.domain.medic;

import com.wareline.agenda.shared.validation.Error;
import com.wareline.agenda.shared.validation.ValidationHandlerInterface;
import com.wareline.agenda.shared.validation.Validator;
import com.wareline.agenda.shared.vo.AddressVO;

public class MedicValidator extends Validator {

    private static final int NAME_MAX_LENGTH = 100;
    private static final int NAME_MIN_LENGTH = 3;
    private static final int CRM_MAX_LENGTH = 20;
    private static final int CRM_MIN_LENGTH = 1;
    private static final int PHONE_MAX_LENGTH = 15;
    private static final int PHONE_MIN_LENGTH = 8;
    private static final int EMAIL_MAX_LENGTH = 100;
    private static final int EMAIL_MIN_LENGTH = 3;

    private static final String ERROR_NAME_EMPTY = "Nome do médico não pode ser vazio";
    private static final String ERROR_NAME_TOO_LONG = "Nome do médico não pode ter mais de " + NAME_MAX_LENGTH
            + " caracteres";
    private static final String ERROR_NAME_TOO_SHORT = "Nome do médico não pode ter menos de " + NAME_MIN_LENGTH
            + " caracteres";
    private static final String ERROR_NAME_INVALID = "Nome do médico não pode conter números";

    private static final String ERROR_CRM_EMPTY = "CRM do médico não pode ser vazio";
    private static final String ERROR_CRM_TOO_LONG = "CRM do médico não pode ter mais de " + CRM_MAX_LENGTH
            + " caracteres";
    private static final String ERROR_CRM_TOO_SHORT = "CRM do médico não pode ter menos de " + CRM_MIN_LENGTH
            + " caracteres";

    private static final String ERROR_PHONE_EMPTY = "Telefone do médico não pode ser vazio";
    private static final String ERROR_PHONE_TOO_LONG = "Telefone do médico não pode ter mais de " + PHONE_MAX_LENGTH
            + " caracteres";
    private static final String ERROR_PHONE_TOO_SHORT = "Telefone do médico não pode ter menos de " + PHONE_MIN_LENGTH
            + " caracteres";
    private static final String ERROR_PHONE_INVALID = "Telefone do médico não pode conter letras";

    private static final String ERROR_EMAIL_EMPTY = "Email do médico não pode ser vazio";
    private static final String ERROR_EMAIL_TOO_LONG = "Email do médico não pode ter mais de " + EMAIL_MAX_LENGTH
            + " caracteres";
    private static final String ERROR_EMAIL_TOO_SHORT = "Email do médico não pode ter menos de " + EMAIL_MIN_LENGTH
            + " caracteres";
    private static final String ERROR_EMAIL_INVALID = "Email do médico inválido";

    private static final String ERROR_ADDRESS_EMPTY = "Endereços do médico não podem ser vazios";

    private final MedicEntity medicoEntity;

    public MedicValidator(final MedicEntity aMedicoEntity, final ValidationHandlerInterface aHandler) {
        super(aHandler);
        this.medicoEntity = aMedicoEntity;
    }

    @Override
    public void validate() {
        validateName();
        validateCrm();
        validatePhone();
        validateEmail();
        validateEnderecos();
    }

    private void validateName() {
        String nome = medicoEntity.getName();
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

    private void validateCrm() {
        String crm = medicoEntity.getCrm();
        if (isNullOrEmpty(crm)) {
            validationHandler().append(new Error("crm", ERROR_CRM_EMPTY));
        } else {
            if (crm.length() > CRM_MAX_LENGTH) {
                validationHandler().append(new Error("crm", ERROR_CRM_TOO_LONG));
            }
            if (crm.length() < CRM_MIN_LENGTH) {
                validationHandler().append(new Error("crm", ERROR_CRM_TOO_SHORT));
            }
            // Adicione aqui uma validação específica para CRM, se necessário
        }
    }

    private void validatePhone() {
        String telefone = medicoEntity.getPhone();
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
        String email = medicoEntity.getEmail();
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
        AddressVO enderecos = medicoEntity.getAddress();

        if (enderecos == null) {
            validationHandler().append(new Error("address", ERROR_ADDRESS_EMPTY));
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
