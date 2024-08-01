package com.wareline.agenda.infra.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "medics")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicModel {

    @Id
    private String id;
    private String name;
    private String crm;
    private String phone;
    private String email;
    private String specialty;
    @Embedded
    private AddressEmbedModel address;
}
