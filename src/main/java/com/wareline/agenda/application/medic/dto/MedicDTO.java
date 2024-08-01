package com.wareline.agenda.application.medic.dto;

import com.wareline.agenda.shared.dtos.address.AddressDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MedicDTO {

    private String id;

    @Size(min = 3, max = 100, message = "Name should have between 3 and 100 characters")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Size(min = 1, max = 20, message = "CRM should have between 1 and 20 characters")
    @NotBlank(message = "CRM is mandatory")
    private String crm;

    @Size(min = 8, max = 15, message = "Phone should have between 8 and 15 characters")
    @NotBlank(message = "Phone is mandatory")
    private String phone;

    @Size(min = 3, max = 100, message = "Email should have between 3 and 100 characters")
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 3, max = 100, message = "Specialty should have between 3 and 100 characters")
    @NotBlank(message = "Specialty is mandatory")
    private String specialty;

    @Valid
    private AddressDTO address;
}
