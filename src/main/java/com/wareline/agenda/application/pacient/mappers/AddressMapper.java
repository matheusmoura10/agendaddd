package com.wareline.agenda.application.pacient.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.wareline.agenda.infra.model.AddressPacientModel;
import com.wareline.agenda.shared.vo.AddressVO;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(target = "id", ignore = true)
    AddressPacientModel toModel(AddressVO addressVO);

    AddressVO toVO(AddressPacientModel addressModel);
}