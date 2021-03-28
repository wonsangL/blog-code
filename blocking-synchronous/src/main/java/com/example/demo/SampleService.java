package com.example.demo;

import com.example.demo.domain.Player;
import com.example.demo.repo.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class SampleService {
    private final PlayerRepository playerRepository;

    public Player getAdultPlayer(String name, int age) {
        Future<Boolean> isAdult = isAdultAsync(age);

        Mono<Player> playerMono = playerRepository.findAllByNameAndAgeWithDelay(name, age);

        return playerMono.block();
    }

    private boolean isAdultSync(int age) throws InterruptedException {
        Thread.sleep(3000);
        return age > 18;
    }


    private Future<Boolean> isAdultAsync(int age) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        return executorService.submit(() -> {
            Thread.sleep(3000);
            return age > 18;
        });
    }
}
