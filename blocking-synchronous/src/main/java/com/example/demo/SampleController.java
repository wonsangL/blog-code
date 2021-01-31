package com.example.demo;

import com.example.demo.domain.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleController {
    private final SampleService sampleService;

    @GetMapping("/player")
    public Player getPlayerWithId(@RequestParam int id) throws Exception {
        return sampleService.getAdultPlayer(id);
    }
}
