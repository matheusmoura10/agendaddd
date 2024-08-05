package com.wareline.agenda.application.medic.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.wareline.agenda.application.medic.dto.MedicDTO;
import com.wareline.agenda.domain.medic.MedicEntity;
import com.wareline.agenda.domain.speciality.SpecialityEntity;
import com.wareline.agenda.infra.model.MedicModel;
import com.wareline.agenda.infra.model.SpecialityModel;

@Mapper(componentModel = "spring", uses = AddressMedicMapper.class)
public interface MedicMapper {

    MedicMapper INSTANCE = Mappers.getMapper(MedicMapper.class);

    @Mapping(target = "specialities", source = "specialities", qualifiedByName = "specialityIdsToEntities")
    MedicEntity toEntity(MedicDTO medicDTO);

    @Mapping(target = "id", source = "id.value")
    MedicModel toModel(MedicEntity medicEntity);

    @Mapping(target = "address.complement", ignore = true)
    @Mapping(target = "specialities", source = "specialities", qualifiedByName = "specialityModelsToIds")
    MedicDTO toDTO(MedicModel medicModel);

    @Named("specialityModelsToIds")
    default List<Long> specialityModelsToIds(List<SpecialityModel> specialities) {
        return specialities.stream()
                .map(SpecialityModel::getId)
                .collect(Collectors.toList());
    }

    @Named("specialityIdsToEntities")
    default List<SpecialityEntity> specialityIdsToEntities(List<Long> specialities) {
        return specialities.stream()
                .map(id -> SpecialityEntity.builder().id(id).build())
                .collect(Collectors.toList());
    }

    MedicEntity toEntity(MedicModel model);
}