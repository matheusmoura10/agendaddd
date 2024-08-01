package com.wareline.agenda.application.pacient.dto;

import java.util.Set;

import com.wareline.agenda.shared.annotations.cpf.CPFValidator;
import com.wareline.agenda.shared.dtos.address.AddressDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PacientDTO {

    private String id;

    @Size(min = 3, max = 100, message = "Name should have between 3 and 100 characters")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Size(min = 3, max = 100, message = "Email should have between 3 and 100 characters")
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 11, max = 11, message = "CPF should have 11 characters")
    @NotBlank(message = "CPF is mandatory")

    @CPFValidator
    private String cpf;

    @Size(min = 10, max = 11, message = "Phone should have between 10 and 11 characters")
    @NotBlank(message = "Phone is mandatory")
    private String phone;

    @Size(min = 1, message = "Should have at least one address")
    @NotNull(message = "Address is mandatory")
    @Valid
    Set<AddressDTO> address;
}
