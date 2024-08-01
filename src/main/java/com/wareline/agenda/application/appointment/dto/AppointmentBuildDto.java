package com.wareline.agenda.application.appointment.dto;

import java.util.List;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AppointmentBuildDto {

    @Valid
    private TargetDateDto dateAvailable;

    private String medicId;

    private List<AppointmentAvaliableDTO> availableTimes;

}
