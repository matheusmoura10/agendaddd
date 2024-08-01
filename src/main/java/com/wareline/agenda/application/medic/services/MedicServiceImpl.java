package com.wareline.agenda.application.medic.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wareline.agenda.application.medic.dto.MedicDTO;
import com.wareline.agenda.application.medic.mappers.MedicMapper;
import com.wareline.agenda.domain.medic.MedicEntity;
import com.wareline.agenda.infra.model.MedicModel;
import com.wareline.agenda.infra.repository.MedicRepository;
import com.wareline.agenda.shared.dtos.pagination.GenericSpecification;
import com.wareline.agenda.shared.dtos.pagination.PaginationDTO;
import com.wareline.agenda.shared.dtos.pagination.PaginationRequestDTO;
import com.wareline.agenda.shared.dtos.response.NotFoundException;

import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicServiceImpl implements MedicService {

    private final MedicRepository medicRepository;
    private final MedicMapper medicMapper;

    public MedicServiceImpl(MedicRepository medicRepository, MedicMapper medicMapper) {
        this.medicRepository = medicRepository;
        this.medicMapper = medicMapper;
    }

    @Override
    public MedicDTO create(MedicDTO medicDTO) {

        MedicEntity medicEntity = medicMapper.toEntity(medicDTO);
        MedicModel medicModel = medicMapper.toModel(medicEntity);

        medicModel = medicRepository.save(medicModel);

        medicDTO = medicMapper.toDTO(medicModel);

        return medicDTO;
    }

    @Override
    public MedicDTO update(String id, MedicDTO medicDTO) {

        medicDTO.setId(id);

        boolean exists = medicRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("Medic not found");
        }

        MedicEntity medicEntity = medicMapper.toEntity(medicDTO);
        MedicModel medicModel = medicMapper.toModel(medicEntity);

        medicModel = medicRepository.save(medicModel);

        medicDTO = medicMapper.toDTO(medicModel);

        return medicDTO;
    }

    @Override
    public void delete(String id) {

        boolean exists = medicRepository.existsById(id);
        if (!exists) {
            log.error("Medic not found");
            throw new NotFoundException("Medic not found");
        }

        medicRepository.deleteById(id);
    }

    @Override
    public MedicDTO findById(String id) {

        MedicModel medicModel = medicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Medic not found"));

        return medicMapper.toDTO(medicModel);
    }

    @Override
    public PaginationDTO<MedicDTO> findAll(PaginationRequestDTO<MedicModel> paginationRequestDTO) {
        int page = paginationRequestDTO.getPage();
        int size = paginationRequestDTO.getSize();
        List<GenericSpecification<MedicModel>> specifications = paginationRequestDTO.getSpecifications();

        PageRequest pageable = PageRequest.of(page, size);
        Specification<MedicModel> specification = (root, query, criteriaBuilder) -> {
            if (specifications == null || specifications.isEmpty()) {
                return criteriaBuilder.conjunction(); // Retorna todos os registros
            }
            Predicate[] predicates = specifications.stream()
                    .map(spec -> spec.toPredicate(root, query, criteriaBuilder))
                    .toArray(Predicate[]::new);
            return criteriaBuilder.and(predicates);
        };

        Page<MedicModel> result = medicRepository.findAll(specification, pageable);

        List<MedicDTO> medicDTOs = result.getContent().stream()
                .map(medicMapper::toDTO)
                .collect(Collectors.toList());

        return new PaginationDTO<>(
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                medicDTOs);
    }

    @Override
    public MedicEntity findByIdEntity(String id) {
        MedicModel model = medicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Medic not found"));

        return medicMapper.toEntity(model);
    }

}
