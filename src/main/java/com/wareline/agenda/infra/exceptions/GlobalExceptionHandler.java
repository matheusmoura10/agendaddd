package com.wareline.agenda.infra.exceptions;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wareline.agenda.shared.dtos.response.GenericError;
import com.wareline.agenda.shared.dtos.response.GenericResponse;
import com.wareline.agenda.shared.dtos.response.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<GenericResponse<List<Map<String, String>>>> handleValidationException(
                        MethodArgumentNotValidException ex) {

                List<Map<String, String>> errors = ex.getBindingResult().getAllErrors().stream()
                                .map(error -> {
                                        FieldError fieldError = (FieldError) error;
                                        return Map.of(
                                                        "field", fieldError.getField(),
                                                        "message", error.getDefaultMessage());
                                })
                                .collect(Collectors.toList());

                return ResponseEntity.badRequest().body(GenericResponse.<List<Map<String, String>>>builder()
                                .code(400)
                                .message("Validation error")
                                .data(errors)
                                .build());
        }

        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<GenericResponse<String>> handleNotFoundException(NotFoundException ex) {
                return ResponseEntity.status(ex.getStatus()).body(GenericResponse.<String>builder()
                                .code(ex.getStatus())
                                .message(ex.getMessage())
                                .data(null)
                                .build());
        }

        @ExceptionHandler(GenericError.class)
        public ResponseEntity<GenericResponse<String>> handleNotFoundException(GenericError ex) {
                return ResponseEntity.status(ex.getStatus()).body(GenericResponse.<String>builder()
                                .code(ex.getStatus())
                                .message(ex.getMessage())
                                .data(null)
                                .build());
        }

        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<GenericResponse<String>> handleDataIntegrityViolationException(
                        org.springframework.dao.DataIntegrityViolationException ex) {

                String message = ex.getCause().getCause().getMessage();

                return ResponseEntity.badRequest().body(GenericResponse.<String>builder()
                                .code(400)
                                .message(message)
                                .data(null)
                                .build());
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<GenericResponse<String>> handleHttpMessageNotReadable(
                        HttpMessageNotReadableException ex) {
                return ResponseEntity.badRequest().body(GenericResponse.<String>builder()
                                .code(400)
                                .message("Invalid request, check the body")
                                .data(null)
                                .build());
        }

}
