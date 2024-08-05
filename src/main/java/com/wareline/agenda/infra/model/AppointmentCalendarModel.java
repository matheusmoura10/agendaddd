package com.wareline.agenda.infra.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "appointment_calendar")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppointmentCalendarModel {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "medic_id")
    private MedicModel medic;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String daysOfWeek;

    private Integer duration;

    private Boolean suppressAppointment;

}
