package com.wareline.agenda.domain.appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.wareline.agenda.application.appointment.dto.AppointmentAvaliableDTO;
import com.wareline.agenda.application.appointment.dto.AppointmentBuildDto;
import com.wareline.agenda.domain.medic.MedicEntity;
import com.wareline.agenda.shared.annotations.daterange.DateRangeDto;
import com.wareline.agenda.shared.annotations.hourrange.HourRangeDto;
import com.wareline.agenda.shared.entities.AggregateRoot;
import com.wareline.agenda.shared.validation.ValidationHandlerInterface;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class AppointmentEntity extends AggregateRoot<AppointmentID> {

    private MedicEntity medicEntity;
    private DateRangeDto dateRange;
    private String daysOfWeek;
    private HourRangeDto hourRange;
    private Integer duration;
    private Boolean suppressAppointment;

    public AppointmentEntity(String id, MedicEntity medicEntity, DateRangeDto dateRange, String daysOfWeek,
            HourRangeDto hourRange, Integer duration, Boolean suppressAppointment) {
        super((id == null) ? AppointmentID.unique() : AppointmentID.from(id));
        this.medicEntity = medicEntity;
        this.dateRange = dateRange;
        this.daysOfWeek = daysOfWeek;
        this.hourRange = hourRange;
        this.duration = duration;
        this.suppressAppointment = suppressAppointment;
    }

    @Override
    public void validate(ValidationHandlerInterface handler) {
        throw new UnsupportedOperationException("Unimplemented method 'validate'");
    }

    public List<AppointmentAvaliableDTO> buildAppointment(AppointmentBuildDto dto) {
        LocalDate dateTarget = dto.getDateAvailable().getDate();
        if (!isValidDay(dateTarget)) {
            return new ArrayList<>();
        }

        LocalDateTime start = dateTarget.atTime(hourRange.getStart());
        LocalDateTime end = dateTarget.atTime(hourRange.getEnd());

        return generateAvailableTimes(start, end);
    }

    private boolean isValidDay(LocalDate date) {
        List<String> validDays = Arrays.asList(daysOfWeek.split(","))
                .stream()
                .map(String::trim)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        return validDays.contains(String.valueOf(date.getDayOfWeek().getValue()));
    }

    private List<AppointmentAvaliableDTO> generateAvailableTimes(LocalDateTime start, LocalDateTime end) {
        List<AppointmentAvaliableDTO> availableTimes = new ArrayList<>();
        LocalDateTime current = start;

        while (!current.plusMinutes(duration).isAfter(end)) {
            availableTimes.add(
                    AppointmentAvaliableDTO.builder()
                            .dateStart(current)
                            .dateEnd(current.plusMinutes(duration))
                            .availableDay(true)
                            .build());
            current = current.plusMinutes(duration);
        }

        return availableTimes;
    }

}
