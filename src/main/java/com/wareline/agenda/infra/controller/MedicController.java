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

import com.wareline.agenda.application.medic.dto.MedicDTO;
import com.wareline.agenda.application.medic.services.MedicService;
import com.wareline.agenda.infra.model.MedicModel;
import com.wareline.agenda.shared.dtos.pagination.PaginationDTO;
import com.wareline.agenda.shared.dtos.pagination.PaginationRequestDTO;
import com.wareline.agenda.shared.dtos.response.GenericResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;

@RestController
@RequestMapping("/medic")
public class MedicController {

    private final MedicService medicService;

    public MedicController(MedicService medicService) {
        this.medicService = medicService;
    }

    @PostMapping()
    public ResponseEntity<GenericResponse<MedicDTO>> create(@Valid @RequestBody MedicDTO entity) {

        entity = medicService.create(entity);

        return ResponseEntity.ok(GenericResponse.<MedicDTO>builder()
                .code(201)
                .message("Medic created successfully")
                .data(entity)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<MedicDTO>> update(@PathVariable String id, @RequestBody MedicDTO entity) {

        entity = medicService.update(id, entity);

        return ResponseEntity.ok(GenericResponse.<MedicDTO>builder()
                .code(200)
                .message("Medic updated successfully")
                .data(entity)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<MedicDTO>> findById(@PathVariable String id) {

        MedicDTO entity = medicService.findById(id);

        return ResponseEntity.ok(GenericResponse.<MedicDTO>builder()
                .code(200)
                .message("Medic found successfully")
                .data(entity)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Null>> delete(@PathVariable String id) {

        medicService.delete(id);

        return ResponseEntity.ok(GenericResponse.<Null>builder()
                .code(200)
                .message("Medic deleted successfully")
                .build());
    }

    @PostMapping("list")
    public ResponseEntity<PaginationDTO<MedicDTO>> index(@RequestBody PaginationRequestDTO<MedicModel> entity) {

        PaginationDTO<MedicDTO> response = medicService.findAll(entity);

        return ResponseEntity.ok().body(response);
    }

}
