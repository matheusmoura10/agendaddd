package com.wareline.agenda.infra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wareline.agenda.application.appointment.dto.AppointmentBuildDto;
import com.wareline.agenda.application.appointment.dto.AppointmentDTO;
import com.wareline.agenda.application.appointment.service.AppointmentService;
import com.wareline.agenda.shared.dtos.response.GenericResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("")
    public ResponseEntity<GenericResponse<AppointmentDTO>> create(@Valid @RequestBody AppointmentDTO entity) {

        entity = appointmentService.create(entity);

        return ResponseEntity.ok(GenericResponse.<AppointmentDTO>builder()
                .code(201)
                .message("Appointment created successfully")
                .data(entity)
                .build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<AppointmentDTO>> update(@PathVariable String id,
            @RequestBody AppointmentDTO entity) {

        entity = appointmentService.update(id, entity);

        return ResponseEntity.ok(GenericResponse.<AppointmentDTO>builder()
                .code(200)
                .message("Appointment updated successfully")
                .data(entity)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<AppointmentDTO>> show(@PathVariable String id) {

        AppointmentDTO entity = appointmentService.findById(id);

        return ResponseEntity.ok(GenericResponse.<AppointmentDTO>builder()
                .code(200)
                .message("Appointment retrieved successfully")
                .data(entity)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<AppointmentDTO>> delete(@PathVariable String id) {

        appointmentService.delete(id);

        return ResponseEntity.ok(GenericResponse.<AppointmentDTO>builder()
                .code(200)
                .message("Appointment deleted successfully")
                .build());
    }

    @PostMapping("/find")
    public AppointmentBuildDto findAppointmend(@RequestBody AppointmentBuildDto entity) {

        return appointmentService.build(entity);
    }

}
