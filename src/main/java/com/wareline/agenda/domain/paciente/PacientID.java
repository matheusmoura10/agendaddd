package com.wareline.agenda.domain.paciente;

import java.util.Objects;

import com.wareline.agenda.shared.helpers.IdHelper;
import com.wareline.agenda.shared.vo.Identifier;

public class PacientID extends Identifier {
    private final String value;

    private PacientID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static PacientID unique() {
        return PacientID.from(IdHelper.uuid());
    }

    public static PacientID from(final String anId) {
        return new PacientID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final PacientID that = (PacientID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return "PacienteID{" +
                "value='" + value + '\'' +
                '}';
    }
}