package com.example.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SampleController {
    @Autowired
    private SampleService service;

    @PostMapping("/signin")
    public boolean signin(@Valid @RequestBody User user){
        return service.signin(user);
    }
}
