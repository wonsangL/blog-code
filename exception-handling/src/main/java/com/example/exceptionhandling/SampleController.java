package com.example.exceptionhandling;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @GetMapping
    public String hello(@RequestParam String name){
        return "Hello! " + name;
    }
}
