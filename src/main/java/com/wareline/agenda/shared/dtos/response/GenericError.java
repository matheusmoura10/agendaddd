package com.wareline.agenda.shared.dtos.response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericError extends RuntimeException {

    private final int status;

    public GenericError(String message) {
        super(message);
        this.status = 400;
    }

    public GenericError(int status, String message) {
        super(message);
        this.status = status;
    }
}