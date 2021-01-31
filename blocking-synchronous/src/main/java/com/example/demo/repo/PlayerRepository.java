package com.example.demo.repo;

import com.example.demo.domain.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
    @Query(nativeQuery = true, value = "call player_select(:id)")
    Player findByIdWithDelay(@Param("id") int id);
}
