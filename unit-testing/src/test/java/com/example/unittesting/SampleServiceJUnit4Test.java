package com.example.unittesting;

import com.example.unittesting.exception.PlayerNotFoundException;
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SampleServiceJUnit4Test {
    @InjectMocks
    private SampleService service;

    @Mock
    private PlayerRepository repository;

    @Test
    public void assumptionTest() {
    }

    @Rule
    public SampleRule rule = new SampleRule();

    @Rule
    public Stopwatch stopwatch = new Stopwatch();

    @Before
    public void setup() {
        System.err.println("before!");
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

        Assertions.assertEquals(player.getId(), result.getId());
        System.err.println("this code will execute");

        Assertions.assertEquals(player.getId(), result.getId() + 1);
        System.err.println("this code won't execute");

        Assertions.assertEquals(player.getCity(), result.getCity());
        System.err.println("this code won't execute neither");
    }

    @Test
    public void getStatusWithInvalidPlayerTest() {
        Optional<Player> result = Optional.empty();

        when(repository.findById(anyInt())).thenReturn(result);
        service.getStatus(1);

        Assert.assertThrows(PlayerNotFoundException.class, this::sample);
    }

    public String sample() {
        throw new PlayerNotFoundException("player not found");
    }

    @Test
    public void getStatusWithInvalidPlayerCatchExceptionTest() {
        Optional<Player> result = Optional.empty();

        when(repository.findById(anyInt())).thenReturn(result);
        PlayerNotFoundException exception = Assert.assertThrows(PlayerNotFoundException.class, () -> service.getStatus(1));
        Assert.assertEquals("player not found", exception.getMessage());
    }


    @Test
    public void signInWithNullCityTest() {
        ReflectionTestUtils.setField(service, "defaultCity", City.ASAN);

        Player player = new Player();
        player.setBudget(BigDecimal.valueOf(1.1));

        service.signIn(player);

        Assert.assertEquals(City.ASAN, player.getCity());
    }
}