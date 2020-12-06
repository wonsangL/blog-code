package com.example.jpa;

import com.example.jpa.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SampleController {
    private SampleService sampleService;

    public SampleController(@Autowired SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/player")
    public Player getPlayer(@RequestParam int id) {
        return sampleService.getPlayer(id);
    }

    @GetMapping("/players")
    public List<Player> getPlayers(@RequestParam String name) {
        return sampleService.getPlayers(name);
    }
}
