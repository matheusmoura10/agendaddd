package com.wareline.agenda.infra.model;

import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    @ManyToMany
    @JoinTable(name = "medic_speciality", joinColumns = @JoinColumn(name = "medic_id"), inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private List<SpecialityModel> specialities;

    @Embedded
    private AddressEmbedModel address;
}
