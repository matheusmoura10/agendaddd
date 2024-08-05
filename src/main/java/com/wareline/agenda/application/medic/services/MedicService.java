package com.wareline.agenda.application.medic.services;

import com.wareline.agenda.application.medic.dto.MedicDTO;
import com.wareline.agenda.domain.medic.MedicEntity;
import com.wareline.agenda.infra.model.MedicModel;
import com.wareline.agenda.shared.dtos.pagination.PaginationDTO;
import com.wareline.agenda.shared.dtos.pagination.PaginationRequestDTO;

public interface MedicService {

    MedicEntity findByIdEntity(String id);

    MedicDTO create(MedicDTO medicDTO);

    MedicDTO update(String id, MedicDTO medicDTO);

    void delete(String id);

    MedicEntity findById(String id);

    public PaginationDTO<MedicDTO> findAll(PaginationRequestDTO<MedicModel> paginationRequestDTO);
}
