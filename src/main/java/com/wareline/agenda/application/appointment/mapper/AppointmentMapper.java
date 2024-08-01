package com.wareline.agenda.application.appointment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.wareline.agenda.application.appointment.dto.AppointmentDTO;
import com.wareline.agenda.domain.appointment.AppointmentEntity;
import com.wareline.agenda.domain.appointment.AppointmentID;
import com.wareline.agenda.domain.medic.MedicID;
import com.wareline.agenda.infra.model.AppointmentCalendarModel;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    AppointmentEntity toEntity(AppointmentDTO appointmentDTO);

    AppointmentCalendarModel toModel(AppointmentDTO appointmentDTO);

    @Mapping(target = "dateRange.start", source = "startDate")
    @Mapping(target = "dateRange.end", source = "endDate")
    @Mapping(target = "hourRange.start", source = "startTime")
    @Mapping(target = "hourRange.end", source = "endTime")
    @Mapping(target = "medicId", source = "medic.id")
    AppointmentDTO toDTO(AppointmentCalendarModel save);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "startDate", source = "dateRange.start")
    @Mapping(target = "endDate", source = "dateRange.end")
    @Mapping(target = "startTime", source = "hourRange.start")
    @Mapping(target = "endTime", source = "hourRange.end")
    @Mapping(target = "medic.id", source = "medicEntity.id", qualifiedByName = "mapMedicIdToString")
    AppointmentCalendarModel toModel(AppointmentEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "dateRange.start", source = "startDate")
    @Mapping(target = "dateRange.end", source = "endDate")
    @Mapping(target = "hourRange.start", source = "startTime")
    @Mapping(target = "hourRange.end", source = "endTime")
    @Mapping(target = "medicEntity.id", source = "medic.id")
    AppointmentEntity toEntity(AppointmentCalendarModel model);

    String map(AppointmentID value);

    AppointmentDTO toDTO(AppointmentEntity entity);

    @Named("mapMedicIdToString")
    default String mapMedicIdToString(MedicID medicID) {
        return medicID != null ? medicID.getValue() : null;
    }

}
