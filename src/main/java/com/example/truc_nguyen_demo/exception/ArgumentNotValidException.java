package com.example.truc_nguyen_demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ArgumentNotValidException extends RuntimeException {
    public ArgumentNotValidException(String message)
    {
        super(message);
    }
}
