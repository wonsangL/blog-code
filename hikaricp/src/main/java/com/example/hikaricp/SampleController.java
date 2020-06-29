package com.example.hikaricp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class SampleController {
    @Autowired
    private SampleService service;

    @PostMapping("/")
    public boolean getUser(@RequestParam String name) throws SQLException {
        return service.join(name);
    }
}
