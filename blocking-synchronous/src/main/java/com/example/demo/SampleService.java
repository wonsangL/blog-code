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

        int age = player.getAge();

//        Future<Boolean> isAdult = isAdultAsync(age);
//
//        Thread.sleep(5000);
//
//        if (isAdult.get()) {
//            return player;
//        }

        if (isAdultSync(age)) {
            return player;
        }

        return null;
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
