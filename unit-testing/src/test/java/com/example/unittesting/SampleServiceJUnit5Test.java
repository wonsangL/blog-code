package com.example.unittesting;

import com.example.unittesting.exception.PlayerNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, SampleExtension.class})
@SpringBootTest
class SampleServiceJUnit5Test {
    @InjectMocks
    private SampleService service;

    @Mock
    private PlayerRepository repository;

    @Test
    public void assumptionTest() {
        System.err.println("outer");
    }


    @Test
    public void getStatusTest() {
        Player player = new Player();
        player.setId(1);
        player.setBudget(BigDecimal.valueOf(10.0));
        player.setCity(City.ANSEONG);

        Optional<Player> optionalPlayer = Optional.of(player);
        when(repository.findById(anyInt())).thenReturn(optionalPlayer);

        Player result = service.getStatus(10);

        Assertions.assertAll("heading",
                // subsequent code in the same block will be skipped.
                () -> {
                    Assertions.assertEquals(player.getId(), result.getId());
                    System.err.println("this code will execute");
                },
                () -> {
                    Assertions.assertEquals(player.getBudget(), result.getBudget());
                    throw new PlayerNotFoundException("player not found");
                },
                () -> {
                    throw new PlayerNotFoundException("player not found");
                },
                () -> {
                    Assertions.assertEquals(player.getCity(), result.getCity());
                    System.err.println("this code will execute");
                });
    }

    @Test
    public void timeoutTest() {
        String result = Assertions.assertTimeout(Duration.ofSeconds(1), () -> "success!");
        System.err.println(result);
    }

    @Test
    public void timeoutFailTest() {
        String result = Assertions.assertTimeout(Duration.ofSeconds(1), () -> {
            Thread.sleep(1001);
            return "success!";
        });
        System.err.println(result);
    }

    @Test
    public void getStatusWithInvalidPlayerTest() {
        Optional<Player> result = Optional.empty();

        when(repository.findById(anyInt())).thenReturn(result);
        PlayerNotFoundException exception =
                Assertions.assertThrows(PlayerNotFoundException.class, () -> service.getStatus(1));

        Assertions.assertEquals("player not found", exception.getMessage());
    }


    @Test
    public void signInTest() {
        Player newPlayer = new Player();
        newPlayer.setCity(City.ANDONG);
        newPlayer.setBudget(BigDecimal.valueOf(10.0));

        when(repository.save(any())).thenReturn(newPlayer);
        Player result = service.signIn(newPlayer);

        Assertions.assertEquals(result, newPlayer);
    }

    @Test
    public void signInWithNullCityTest() {
        ReflectionTestUtils.setField(service, "defaultCity", City.ASAN);

        Player player = new Player();
        player.setBudget(BigDecimal.valueOf(1.1));

        service.signIn(player);

        Assertions.assertEquals(City.ASAN, player.getCity());
    }
}