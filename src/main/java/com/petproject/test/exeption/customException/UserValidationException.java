package com.petproject.test.exeption.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserValidationException extends Exception
{
    public UserValidationException(String message) {
        super(message);
    }
}