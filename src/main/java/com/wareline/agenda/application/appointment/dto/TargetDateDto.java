package com.wareline.agenda.application.appointment.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TargetDateDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    // Default constructor
    public TargetDateDto() {
    }
}