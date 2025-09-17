package com.CSE_D.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {
    
    @ExceptionHandler(IdNotPresentException.class)
    public String handleIdNotPresentException(IdNotPresentException ex) {
        return ex.getMessage();
    }
}
