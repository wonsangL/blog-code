package com.example.springwebflux.chapter02;

import com.example.springwebflux.domain.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/webclient")
public class WebClientController {
    WebClientExample webClientService;

    public WebClientController(@Autowired WebClientExample service) {
        webClientService = service;
    }

    @GetMapping("/player")
    public Mono<Player> getPlayer(@RequestParam int id) {
        Mono<Player> result = webClientService.getPlayer(id);
        log.debug("return response");
        return result;
    }

    @GetMapping("/players")
    public Flux<Player> getPlayers(@RequestParam String name) {
        return webClientService.getPlayers(name);
    }
}
