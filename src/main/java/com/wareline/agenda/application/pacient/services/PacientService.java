package com.wareline.agenda.application.pacient.services;

import com.wareline.agenda.application.pacient.dto.PacientDTO;
import com.wareline.agenda.domain.paciente.PacientEntity;
import com.wareline.agenda.infra.model.PacientModel;
import com.wareline.agenda.shared.dtos.pagination.PaginationDTO;
import com.wareline.agenda.shared.dtos.pagination.PaginationRequestDTO;

public interface PacientService {

    public PacientEntity create(PacientDTO pacientDTO);

    public PacientEntity update(String id, PacientDTO pacientDTO);

    public void delete(String id);

    public PacientDTO findById(String id);

    public PaginationDTO<PacientDTO> findAll(PaginationRequestDTO<PacientModel> paginationRequestDTO);

}
