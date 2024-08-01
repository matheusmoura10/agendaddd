package com.wareline.agenda.application.medic.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.wareline.agenda.infra.model.AddressEmbedModel;
import com.wareline.agenda.shared.vo.AddressVO;

@Mapper(componentModel = "spring")
public interface AddressMedicMapper {

    AddressMedicMapper INSTANCE = Mappers.getMapper(AddressMedicMapper.class);

    AddressEmbedModel toModel(AddressVO addressVO);

    AddressVO toVO(AddressEmbedModel addressEmbedModel);
}