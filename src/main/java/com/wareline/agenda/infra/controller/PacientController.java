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

import com.wareline.agenda.application.pacient.dto.PacientDTO;
import com.wareline.agenda.application.pacient.services.PacientService;
import com.wareline.agenda.domain.paciente.PacientEntity;
import com.wareline.agenda.infra.model.PacientModel;
import com.wareline.agenda.shared.dtos.pagination.PaginationDTO;
import com.wareline.agenda.shared.dtos.pagination.PaginationRequestDTO;
import com.wareline.agenda.shared.dtos.response.GenericResponse;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/pacient")
public class PacientController {

    private final PacientService pacientService;

    PacientController(PacientService pacientService) {
        this.pacientService = pacientService;
    }

    @PostMapping("list")
    public ResponseEntity<PaginationDTO<PacientDTO>> index(@RequestBody PaginationRequestDTO<PacientModel> entity) {

        PaginationDTO<PacientDTO> response = pacientService.findAll(entity);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("")
    public ResponseEntity<GenericResponse<PacientEntity>> create(@Valid @RequestBody PacientDTO pacientDTO) {
        PacientEntity pacienteEntity = pacientService.create(pacientDTO);

        return ResponseEntity.ok(GenericResponse.<PacientEntity>builder()
                .code(201)
                .message("Paciente criado com sucesso")
                .data(pacienteEntity)
                .build());
    }

    @PutMapping("{id}")
    public ResponseEntity<GenericResponse<PacientEntity>> update(@PathVariable String id,
            @RequestBody PacientDTO entity) {
        PacientEntity pacienteEntity = pacientService.update(id, entity);

        return ResponseEntity.ok(GenericResponse.<PacientEntity>builder()
                .code(200)
                .message("Paciente atualizado com sucesso")
                .data(pacienteEntity)
                .build());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericResponse<Void>> delete(@PathVariable String id) {
        pacientService.delete(id);

        return ResponseEntity.ok(GenericResponse.<Void>builder()
                .code(200)
                .message("Paciente deletado com sucesso")
                .build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GenericResponse<PacientDTO>> findById(@PathVariable String id) {
        PacientDTO dto = pacientService.findById(id);

        return ResponseEntity.ok(GenericResponse.<PacientDTO>builder()
                .code(200)
                .message("Paciente encontrado com sucesso")
                .data(dto)
                .build());
    }

}
