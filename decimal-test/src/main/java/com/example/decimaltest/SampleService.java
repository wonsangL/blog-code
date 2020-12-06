package com.example.decimaltest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class SampleService {
    @Autowired
    private PlayerRepository playerRepository;

    public Player findUserById(int id){
        Optional<Player> player = playerRepository.findById(id);

        if(player.isPresent()){
            return player.get();
        }
        return null;
    }

    public void saveUser(Player player){
        playerRepository.save(player);
    }
}
