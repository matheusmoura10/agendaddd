package com.wareline.agenda.application.appointment.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AppointmentAvaliableDTO {

    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private Boolean availableDay;
}
