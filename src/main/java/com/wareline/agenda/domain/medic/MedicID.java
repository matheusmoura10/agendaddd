package com.wareline.agenda.domain.medic;

import java.util.Objects;

import com.wareline.agenda.shared.helpers.IdHelper;
import com.wareline.agenda.shared.vo.Identifier;

public class MedicID extends Identifier {
    private final String value;

    private MedicID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static MedicID unique() {
        return MedicID.from(IdHelper.uuid());
    }

    public static MedicID from(final String anId) {
        return new MedicID(anId);
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
        final MedicID that = (MedicID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return "MedicID{" +
                "value='" + value + '\'' +
                '}';
    }
}