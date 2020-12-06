package com.example.unittesting;

import com.example.unittesting.exception.PlayerNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Executable;
import java.util.Optional;

@Service
public class SampleService {
    @Autowired
    private PlayerRepository playerRepository;

    City defaultCity = City.ANDONG;

    public Player getStatus(int id) {
        Optional<Player> result = playerRepository.findById(id);

        if (!result.isPresent()) {
            throw new PlayerNotFoundException("player not found");
        }

        return result.get();
    }

    public Player signIn(Player player) {
        if (player.getCity() == null) {
            player.setCity(defaultCity);
        }

        return playerRepository.save(player);
    }

    public void voidMethod() {
       //do nothing...
    }
}
