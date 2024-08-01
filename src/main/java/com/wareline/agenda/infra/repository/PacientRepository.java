package com.wareline.agenda.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wareline.agenda.infra.model.PacientModel;

public interface PacientRepository extends JpaRepository<PacientModel, String>, JpaSpecificationExecutor<PacientModel> {

}
