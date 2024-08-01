package com.wareline.agenda.application.medic.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.wareline.agenda.application.medic.dto.MedicDTO;
import com.wareline.agenda.domain.medic.MedicEntity;
import com.wareline.agenda.infra.model.MedicModel;

@Mapper(componentModel = "spring", uses = AddressMedicMapper.class)
public interface MedicMapper {

    MedicMapper INSTANCE = Mappers.getMapper(MedicMapper.class);

    MedicEntity toEntity(MedicDTO medicDTO);

    @Mapping(target = "id", source = "id.value")
    MedicModel toModel(MedicEntity medicEntity);

    @Mapping(target = "address.complement", ignore = true)
    MedicDTO toDTO(MedicModel medicModel);

    @Mapping(target = "id", source = "id.value")

    MedicEntity toDTO(MedicEntity medicEntity);

    MedicEntity toEntity(MedicModel model);

}
