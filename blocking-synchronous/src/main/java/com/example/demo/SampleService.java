package com.example.demo;

import com.example.demo.domain.Player;
import com.example.demo.repo.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class SampleService {
    private final PlayerRepository playerRepository;

    public Player getAdultPlayer(int id) throws InterruptedException, ExecutionException {
        Player player = playerRepository.findByIdWithDelay(id);

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Boolean> isAdult = executorService.submit(() -> {
            Thread.sleep(5000);
            return player.getAge() > 18;
        });

        if (isAdult.get()) {
            return player;
        }

        return null;
    }
}
