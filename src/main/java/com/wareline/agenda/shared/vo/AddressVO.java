package com.wareline.agenda.shared.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressVO extends ValueObject {

    private final String street;
    private final String number;
    private final String complement;
    private final String neighborhood;
    private final String city;
    private final String state;
    private final String zipCode;

    public AddressVO(final String street, final String number, final String complement, final String neighborhood,
            final String city, final String state, final String zipCode) {
        this.street = street;
        this.number = number;
        this.complement = complement == null ? "" : complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AddressVO other = (AddressVO) obj;
        return street.equals(other.street) && number.equals(other.number)
                && complement.equals(other.complement)
                && neighborhood.equals(other.neighborhood) && city.equals(other.city) && state.equals(other.state)
                && zipCode.equals(other.zipCode);
    }

    @Override
    public int hashCode() {
        return street.hashCode() + number.hashCode() + complement.hashCode() + neighborhood.hashCode()
                + city.hashCode() + state.hashCode() + zipCode.hashCode();
    }

    @Override
    public String toString() {
        return "AddressVO [street=" + street + ", number=" + number + ", complement=" + complement
                + ", neighborhood=" + neighborhood + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode
                + "]";
    }
}