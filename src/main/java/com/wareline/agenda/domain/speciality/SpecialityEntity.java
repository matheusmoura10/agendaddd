package com.wareline.agenda.domain.speciality;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SpecialityEntity {

    private long id;
    private String name;
    private String description;
    private Boolean active;

}
