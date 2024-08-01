package com.wareline.agenda.shared.annotations.hourrange;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class HourRangeDto {

    private String start;
    private String end;

    public LocalTime getStart() {
        return LocalTime.parse(start);
    }

    public LocalTime getEnd() {
        return LocalTime.parse(end);
    }
}
