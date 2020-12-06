package com.example.jpa;

import com.example.jpa.entity.Player;
import com.example.jpa.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SampleService {
    private PlayerRepository playerRepository;

    public SampleService(@Autowired PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers(String name) {
        List<Player> result = playerRepository.findAllByName(name);

        if(result.isEmpty()){
            throw new RuntimeException("empty player list");
        }
        return result;
    }

    public Player getPlayer(int id) {
        Optional<Player> result = playerRepository.findById(id);
        return result.orElse(null);
    }
}
