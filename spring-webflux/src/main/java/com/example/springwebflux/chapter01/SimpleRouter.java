package com.example.springwebflux.chapter01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SimpleRouter {
    @Bean
    public RouterFunction<ServerResponse> helloRouter(@Autowired SimpleHandler handler) {
        return RouterFunctions.route(RequestPredicates.path("/{name}"), handler::hello);
    }
}
