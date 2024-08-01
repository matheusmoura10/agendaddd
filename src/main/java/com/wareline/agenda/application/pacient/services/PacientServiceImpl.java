package com.wareline.agenda.application.pacient.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wareline.agenda.application.pacient.dto.PacientDTO;
import com.wareline.agenda.application.pacient.mappers.PacienteMapper;
import com.wareline.agenda.domain.paciente.PacientEntity;
import com.wareline.agenda.infra.model.PacientModel;
import com.wareline.agenda.infra.repository.PacientRepository;
import com.wareline.agenda.shared.dtos.pagination.GenericSpecification;
import com.wareline.agenda.shared.dtos.pagination.PaginationDTO;
import com.wareline.agenda.shared.dtos.pagination.PaginationRequestDTO;
import com.wareline.agenda.shared.dtos.response.NotFoundException;

import jakarta.persistence.criteria.Predicate;

@Service
public class PacientServiceImpl implements PacientService {

    private final PacienteMapper pacientMapper;

    private PacientRepository pacientRepository;

    public PacientServiceImpl(PacienteMapper pacienteMapper, PacientRepository pacientRepository) {
        this.pacientMapper = pacienteMapper;
        this.pacientRepository = pacientRepository;
    }

    @Override
    public PacientEntity create(PacientDTO pacientDTO) {

        PacientEntity pacientEntity = pacientMapper.toEntity(pacientDTO);
        PacientModel pacientModel = pacientMapper.toModel(pacientEntity);

        pacientRepository.save(pacientModel);

        return pacientEntity;
    }

    @Override
    public PacientEntity update(String id, PacientDTO pacientDTO) {

        pacientDTO.setId(id);

        boolean exists = pacientRepository.existsById(id);

        if (!exists) {
            throw new NotFoundException("Paciente não encontrado");
        }

        PacientEntity pacientEntity = pacientMapper.toEntity(pacientDTO);
        PacientModel pacientModel = pacientMapper.toModel(pacientEntity);

        pacientRepository.save(pacientModel);

        return pacientEntity;
    }

    @Override
    public void delete(String id) {

        boolean exists = pacientRepository.existsById(id);

        if (!exists) {
            throw new NotFoundException("Paciente não encontrado");
        }

        pacientRepository.deleteById(id);
    }

    @Override
    public PacientDTO findById(String id) {

        PacientModel pacientModel = pacientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado"));

        PacientEntity pacientEntity = pacientMapper.toEntity(pacientModel);

        return pacientMapper.toDTO(pacientEntity);
    }

    @Override
    public PaginationDTO<PacientDTO> findAll(PaginationRequestDTO<PacientModel> paginationRequestDTO) {

        int page = paginationRequestDTO.getPage();
        int size = paginationRequestDTO.getSize();
        List<GenericSpecification<PacientModel>> specifications = paginationRequestDTO.getSpecifications();

        PageRequest pageable = PageRequest.of(page, size);
        Specification<PacientModel> specification = (root, query, criteriaBuilder) -> {
            if (specifications == null || specifications.isEmpty()) {
                return criteriaBuilder.conjunction(); // Retorna todos os registros
            }
            Predicate[] predicates = specifications.stream()
                    .map(spec -> spec.toPredicate(root, query, criteriaBuilder))
                    .toArray(Predicate[]::new);
            return criteriaBuilder.and(predicates);
        };

        Page<PacientModel> result = pacientRepository.findAll(specification, pageable);

        List<PacientEntity> pacienteEntity = result.stream()
                .map(pacientMapper::toEntity)
                .collect(Collectors.toList());

        List<PacientDTO> pacientDTOs = pacienteEntity.stream()
                .map(pacientMapper::toDTO)
                .collect(Collectors.toList());

        return new PaginationDTO<>(
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                pacientDTOs);

    }
}
