package com.wareline.agenda.infra.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "specialities")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SpecialityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Boolean active;

    @ManyToMany(mappedBy = "specialities")
    private Set<MedicModel> medics;

}
