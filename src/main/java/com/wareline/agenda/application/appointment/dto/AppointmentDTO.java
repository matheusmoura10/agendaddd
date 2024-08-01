package com.wareline.agenda.application.appointment.dto;

import com.wareline.agenda.shared.annotations.daterange.DateRangeDto;
import com.wareline.agenda.shared.annotations.daterange.DateRangeValidator;
import com.wareline.agenda.shared.annotations.hourrange.HourRange;
import com.wareline.agenda.shared.annotations.hourrange.HourRangeDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class AppointmentDTO {

    private String id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String medicId;

    @DateRangeValidator()
    @Valid
    private DateRangeDto dateRange;

    @NotBlank(message = "daysOfWeek is mandatory")
    String daysOfWeek;

    @HourRange()
    @Valid
    private HourRangeDto hourRange;

    private Integer duration;

    private Boolean suppressAppointment;

}
