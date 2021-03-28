package com.example.demo.repo;

import com.example.demo.domain.Player;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

public interface PlayerRepository extends R2dbcRepository<Player, Integer> {
    @Query(value = "do sleep(3); select * from player where name = :name and age = :age;")
    Mono<Player> findAllByNameAndAgeWithDelay(@Param("name") String name, @Param("age") int age);
}
