package com.wareline.agenda.application.appointment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.wareline.agenda.application.appointment.dto.AppointmentAvaliableDTO;
import com.wareline.agenda.application.appointment.dto.AppointmentBuildDto;
import com.wareline.agenda.application.appointment.dto.AppointmentDTO;
import com.wareline.agenda.application.appointment.mapper.AppointmentMapper;
import com.wareline.agenda.application.medic.services.MedicService;
import com.wareline.agenda.domain.appointment.AppointmentEntity;
import com.wareline.agenda.domain.medic.MedicEntity;
import com.wareline.agenda.infra.model.AppointmentCalendarModel;
import com.wareline.agenda.infra.repository.AppointmentRepository;
import com.wareline.agenda.infra.repository.MedicRepository;
import com.wareline.agenda.shared.dtos.pagination.PaginationDTO;
import com.wareline.agenda.shared.dtos.pagination.PaginationRequestDTO;
import com.wareline.agenda.shared.dtos.response.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final MedicService medicService;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper,
            MedicRepository medicRepository, MedicService medicService) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.medicService = medicService;
    }

    @Override
    public AppointmentDTO create(AppointmentDTO appointmentDTO) {

        AppointmentEntity entity = appointmentMapper.toEntity(appointmentDTO);
        entity.setMedicEntity(medicService.findByIdEntity(appointmentDTO.getMedicId()));

        AppointmentCalendarModel model = appointmentMapper.toModel(entity);

        return appointmentMapper.toDTO(appointmentRepository.save(model));
    }

    @Override
    public AppointmentDTO update(String id, AppointmentDTO appointmentDTO) {

        appointmentDTO.setId(id);

        boolean exists = appointmentRepository.existsById(id);

        if (!exists) {
            throw new NotFoundException("Appointment not found");
        }

        AppointmentEntity entity = appointmentMapper.toEntity(appointmentDTO);
        AppointmentCalendarModel model = appointmentMapper.toModel(entity);

        return appointmentMapper.toDTO(appointmentRepository.save(model));
    }

    @Override
    public void delete(String id) {

        boolean exists = appointmentRepository.existsById(id);

        if (!exists) {
            throw new NotFoundException("Appointment not found");
        }

        appointmentRepository.deleteById(id);
    }

    @Override
    public AppointmentDTO findById(String id) {

        AppointmentCalendarModel model = appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Appointment not found"));

        return appointmentMapper.toDTO(model);
    }

    @Override
    public PaginationDTO<AppointmentDTO> findAll(PaginationRequestDTO<AppointmentCalendarModel> paginationRequestDTO) {

        int page = paginationRequestDTO.getPage();
        int size = paginationRequestDTO.getSize();

        PageRequest pageable = PageRequest.of(page, size);

        Page<AppointmentCalendarModel> pageResult = appointmentRepository.findAll(pageable);

        List<AppointmentDTO> appointmentDTOs = pageResult.getContent().stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());

        return new PaginationDTO<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                appointmentDTOs);

    }

    @Override
    public AppointmentBuildDto build(AppointmentBuildDto appointmentDTO) {

        MedicEntity medicEntity = medicService.findByIdEntity(appointmentDTO.getMedicId());

        List<AppointmentEntity> appointmentEntities = appointmentRepository.findAppointmentsByDateRangeAndMedic(
                appointmentDTO.getDateAvailable().getDate(),
                appointmentDTO.getMedicId()).stream()
                .map(appointmentMapper::toEntity)
                .collect(Collectors.toList());

        List<AppointmentAvaliableDTO> availableTimes = new ArrayList<>();

        appointmentEntities.forEach(appointmentEntity -> {
            appointmentEntity.setMedicEntity(medicEntity);
            availableTimes.addAll(appointmentEntity.buildAppointment(appointmentDTO));
        });

        appointmentDTO.setAvailableTimes(availableTimes);

        return appointmentDTO;

    }

}
