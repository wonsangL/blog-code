package com.example.springwebflux.chapter02;

import com.example.springwebflux.domain.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class WebClientExample {
    WebClient webClient;

    public WebClientExample() {
        webClient = WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    public Mono<Player> getPlayer(int id) {
        Mono<Player> result = webClient.get()
                .uri("/player?id={id}", id)
                .retrieve()
                .bodyToMono(Player.class)
                .doOnSuccess(player -> log.debug(player.getName()));

        log.debug("return result");
        return result;
    }

    public Flux<Player> getPlayers(String name) {
        return webClient.get()
                .uri("/players?name={name}", name)
                .retrieve()
                .bodyToFlux(Player.class);
    }
}