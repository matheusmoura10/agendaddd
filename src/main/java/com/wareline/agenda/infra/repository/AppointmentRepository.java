package com.wareline.agenda.infra.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wareline.agenda.infra.model.AppointmentCalendarModel;

public interface AppointmentRepository extends JpaRepository<AppointmentCalendarModel, String> {

    @Query("SELECT a FROM appointment_calendar a WHERE a.startDate <= :date AND a.endDate >= :date AND a.medic.id = :medicId")
    List<AppointmentCalendarModel> findAppointmentsByDateRangeAndMedic(
            @Param("date") LocalDate date,
            @Param("medicId") String medicId);
}
