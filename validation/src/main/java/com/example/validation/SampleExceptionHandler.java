package com.example.validation;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SampleExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String bindExceptionHandler(MethodArgumentNotValidException e) {
        return e.getParameter().getParameterName();
    }
}
