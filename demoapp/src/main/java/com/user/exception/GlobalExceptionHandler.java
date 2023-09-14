package com.user.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        log.info("User not found : {}", e.getMessage());
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Validation Error", List.of(e.getMessage()));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicatedFieldException.class)
    public ResponseEntity<Object> handleDuplicatedFieldException(DuplicatedFieldException e) {
        log.info(e.getMessage());
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Validation Error", List.of(e.getMessage()));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleBadRequestException(MethodArgumentNotValidException e) {
        log.info("Validation Error : {}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        List<String> errors = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String error = fieldError.getField() + ": " + fieldError.getDefaultMessage();
            errors.add(error);
        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation Error", errors);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
