package com.example.unittesting;

import com.example.unittesting.exception.PlayerNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(PlayerNotFoundException.class)
    public String userNotFoundHandler(){
        return "player not found!";
    }
}
