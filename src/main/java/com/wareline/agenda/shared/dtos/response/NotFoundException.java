package com.wareline.agenda.shared.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {

    private final int status;

    public NotFoundException(String message) {
        super(message);
        this.status = 404;
    }

    public NotFoundException(int status, String message) {
        super(message);
        this.status = status;
    }
}