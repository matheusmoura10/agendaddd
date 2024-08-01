package com.wareline.agenda.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wareline.agenda.infra.model.AddressPacientModel;

public interface AddressPacientRepository extends JpaRepository<AddressPacientModel, Long> {

}
