package com.wareline.agenda.infra.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressEmbedModel {

    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;

}
