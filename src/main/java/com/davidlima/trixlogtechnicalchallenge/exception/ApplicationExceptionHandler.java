package com.davidlima.trixlogtechnicalchallenge.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApplicationException> handleNotFoundException(NotFoundException exception) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        ApplicationException applicationException = new ApplicationException(
            exception.getMessage(),
            LocalDateTime.now(),
            status.value(),
            "Resource not found"
        );

        return new ResponseEntity<ApplicationException>(applicationException, status);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApplicationException> handleConflictException(ConflictException exception) {

        HttpStatus status = HttpStatus.CONFLICT;

        ApplicationException applicationException = new ApplicationException(
            exception.getMessage(),
            LocalDateTime.now(),
            status.value(),
            "Resource conflict"
        );

        return new ResponseEntity<ApplicationException>(applicationException, status);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationException> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, String> errors = new HashMap<>();

        exception
            .getBindingResult()
            .getAllErrors()
            .forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });

        ValidationException validationException = new ValidationException(
            "Invalid request body",
            LocalDateTime.now(),
            status.value(),
            errors
        );

        return new ResponseEntity<>(validationException, status);

    }

}
