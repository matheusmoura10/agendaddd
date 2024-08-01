package com.wareline.agenda.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wareline.agenda.infra.model.MedicModel;

public interface MedicRepository extends JpaRepository<MedicModel, String>, JpaSpecificationExecutor<MedicModel> {

}
