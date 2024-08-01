package com.wareline.agenda.application.appointment.service;

import com.wareline.agenda.application.appointment.dto.AppointmentBuildDto;
import com.wareline.agenda.application.appointment.dto.AppointmentDTO;
import com.wareline.agenda.infra.model.AppointmentCalendarModel;
import com.wareline.agenda.shared.dtos.pagination.PaginationDTO;
import com.wareline.agenda.shared.dtos.pagination.PaginationRequestDTO;

public interface AppointmentService {

    AppointmentDTO create(AppointmentDTO appointmentDTO);

    AppointmentDTO update(String id, AppointmentDTO appointmentDTO);

    void delete(String id);

    AppointmentDTO findById(String id);

    PaginationDTO<AppointmentDTO> findAll(PaginationRequestDTO<AppointmentCalendarModel> paginationRequestDTO);

    AppointmentBuildDto build(AppointmentBuildDto appointmentDTO);

}
