package com.wareline.agenda.shared.annotations.daterange;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DateRangeDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;

    public boolean isBetween(LocalDate dateTarget) {

        return dateTarget.isAfter(start) && dateTarget.isBefore(end);
    }

}