package com.wareline.agenda.shared.dtos.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Setter;

@lombok.Getter
@Setter
@lombok.AllArgsConstructor
public class AddressDTO {

        @NotBlank(message = "The ZIP code is required")
        @Size(min = 9, max = 9, message = "The ZIP code must be 9 characters long")
        private String zipCode;

        @NotBlank(message = "The street is required")
        @Size(min = 3, max = 100, message = "The street must be between 3 and 100 characters long")
        private String street;

        @NotBlank(message = "The number is required")
        @Size(min = 1, max = 10, message = "The number must be between 1 and 10 characters long")
        private String number;

        private String complement;

        @NotBlank(message = "The neighborhood is required")
        @Size(min = 3, max = 100, message = "The neighborhood must be between 3 and 100 characters long")
        private String neighborhood;

        @NotBlank(message = "The city is required")
        @Size(min = 3, max = 100, message = "The city must be between 3 and 100 characters long")
        private String city;

        @NotBlank(message = "The state is required")
        @Size(min = 2, max = 2, message = "The state must be 2 characters long")
        private String state;

}