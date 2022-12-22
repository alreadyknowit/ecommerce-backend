package com.example.ecommercebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;


@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {

        ExceptionModel exceptionModel = new ExceptionModel(
                -1,
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                ZonedDateTime.now(ZoneId.of("Europe/Istanbul"))
        );

        return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ResourceAlreadyExistException.class})
    public ResponseEntity<Object> handleResourceAlreadyExistException(ResourceAlreadyExistException e) {

        ExceptionModel exceptionModel = new ExceptionModel(
                -1,
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                ZonedDateTime.now(ZoneId.of("Europe/Istanbul"))
        );

        return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AuthException.class})
    public ResponseEntity<Object> handleAuthException(AuthException e) {

        ExceptionModel exceptionModel = new ExceptionModel(
                -1,
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                ZonedDateTime.now(ZoneId.of("Europe/Istanbul"))
        );

        return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_FOUND);
    }
}
