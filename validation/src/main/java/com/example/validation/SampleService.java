package com.example.validation;

import org.springframework.stereotype.Service;

@Service
public class SampleService {
    public boolean signin(User user){
        return !user.getName().isEmpty();
    }
}
