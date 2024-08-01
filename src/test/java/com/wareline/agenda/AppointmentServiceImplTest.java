package com.wareline.agenda;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wareline.agenda.application.appointment.dto.AppointmentDTO;
import com.wareline.agenda.application.appointment.mapper.AppointmentMapper;
import com.wareline.agenda.application.appointment.service.AppointmentServiceImpl;
import com.wareline.agenda.application.medic.services.MedicService;
import com.wareline.agenda.domain.appointment.AppointmentEntity;
import com.wareline.agenda.domain.medic.MedicEntity;
import com.wareline.agenda.infra.model.AppointmentCalendarModel;
import com.wareline.agenda.infra.repository.AppointmentRepository;

class AppointmentServiceImplTest {

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private AppointmentMapper appointmentMapper;

    @Mock
    private MedicService medicService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        AppointmentEntity appointmentEntity = new AppointmentEntity(null, null, null, null, null, null, null);
        AppointmentCalendarModel appointmentModel = new AppointmentCalendarModel();

        when(appointmentMapper.toEntity(appointmentDTO)).thenReturn(appointmentEntity);
        when(medicService.findByIdEntity(anyString()))
                .thenReturn(new MedicEntity(null, null, null, null, null, null, null));
        when(appointmentMapper.toModel(appointmentEntity)).thenReturn(appointmentModel);
        when(appointmentRepository.save(appointmentModel)).thenReturn(appointmentModel);
        when(appointmentMapper.toDTO(appointmentModel)).thenReturn(appointmentDTO);

        AppointmentDTO result = appointmentService.create(appointmentDTO);

        assertNotNull(result);
        verify(appointmentRepository).save(appointmentModel);
    }

    @Test
    void testUpdate() {
        String id = "1";
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        AppointmentEntity appointmentEntity = new AppointmentEntity(id, null, null, id, null, null, null);
        AppointmentCalendarModel appointmentModel = new AppointmentCalendarModel();

        when(appointmentRepository.existsById(id)).thenReturn(true);
        when(appointmentMapper.toEntity(appointmentDTO)).thenReturn(appointmentEntity);
        when(appointmentMapper.toModel(appointmentEntity)).thenReturn(appointmentModel);
        when(appointmentRepository.save(appointmentModel)).thenReturn(appointmentModel);
        when(appointmentMapper.toDTO(appointmentModel)).thenReturn(appointmentDTO);

        AppointmentDTO result = appointmentService.update(id, appointmentDTO);

        assertNotNull(result);
        verify(appointmentRepository).save(appointmentModel);
    }

    @Test
    void testDelete() {
        String id = "1";

        when(appointmentRepository.existsById(id)).thenReturn(true);

        assertDoesNotThrow(() -> appointmentService.delete(id));
        verify(appointmentRepository).deleteById(id);
    }

    @Test
    void testFindById() {
        String id = "1";
        AppointmentCalendarModel appointmentModel = new AppointmentCalendarModel();
        AppointmentDTO appointmentDTO = new AppointmentDTO();

        when(appointmentRepository.findById(id)).thenReturn(Optional.of(appointmentModel));
        when(appointmentMapper.toDTO(appointmentModel)).thenReturn(appointmentDTO);

        AppointmentDTO result = appointmentService.findById(id);

        assertNotNull(result);
        verify(appointmentRepository).findById(id);
    }

}