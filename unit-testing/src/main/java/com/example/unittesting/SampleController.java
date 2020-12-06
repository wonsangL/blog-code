package com.example.unittesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {
    @Autowired
    private SampleService sampleService;

    @GetMapping("status")
    public Player getStatus(int id){
        return sampleService.getStatus(id);
    }

    @PostMapping("signin")
    public Player signIn(@RequestBody Player player){
        return sampleService.signIn(player);
    }

}
