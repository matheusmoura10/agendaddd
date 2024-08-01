package com.wareline.agenda.application.pacient.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.wareline.agenda.application.pacient.dto.PacientDTO;
import com.wareline.agenda.domain.paciente.PacientEntity;
import com.wareline.agenda.infra.model.PacientModel;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface PacienteMapper {

    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    PacientEntity toEntity(PacientDTO pacientDTO);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "addresses", source = "address")
    PacientModel toModel(PacientEntity pacientEntity);

    @Mapping(source = "addresses", target = "address")
    PacientEntity toEntity(PacientModel pacientModel);

    @Mapping(source = "id.value", target = "id")
    PacientDTO toDTO(PacientEntity pacientEntity);
}
