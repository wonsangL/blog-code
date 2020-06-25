package com.example.springwebflux.chapter01;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SimpleHandler {
    public Mono<ServerResponse> hello(ServerRequest req) {
        String res = "Hi! " + req.pathVariable("name");
        return ServerResponse.ok().body(BodyInserters.fromValue(res));
    }
}
