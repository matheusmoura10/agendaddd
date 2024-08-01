package com.wareline.agenda.domain.appointment;

import java.util.Objects;

import com.wareline.agenda.shared.helpers.IdHelper;
import com.wareline.agenda.shared.vo.Identifier;

public class AppointmentID extends Identifier {
    private final String value;

    private AppointmentID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static AppointmentID unique() {
        return AppointmentID.from(IdHelper.uuid());
    }

    public static AppointmentID from(final String anId) {
        return new AppointmentID(anId);
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
        final AppointmentID that = (AppointmentID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return "AppointmentID{" +
                "value='" + value + '\'' +
                '}';
    }
}
