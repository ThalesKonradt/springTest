package com.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DuplicatedFieldException extends RuntimeException {
    public DuplicatedFieldException(String email) {
        super("The email address has already been registered : " + email);
    }
}
