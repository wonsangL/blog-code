package com.example.jpa.repository;

import com.example.jpa.entity.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
    List<Player> findAllByName(String name);
}
