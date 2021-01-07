package com.masiv.test.service;

import com.masiv.test.domain.dto.BetDTO;
import com.masiv.test.domain.dto.ChangeRouletteStatusDTO;
import com.masiv.test.domain.dto.RouletteDTO;
import com.masiv.test.domain.enums.betColor.BetColorType;
import com.masiv.test.exceptions.RouletteException;
import com.masiv.test.infrastructure.repositories.RouletteRepository;
import com.masiv.test.services.RouletteService;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class RouletteServiceTest {

  @Autowired private RouletteService rouletteService;
  @Autowired private RouletteRepository rouletteRepository;
  private String id;
  private RouletteDTO rouletteDTO;

  public void setUp() {
    id = rouletteService.createRoulette();
    rouletteDTO = RouletteDTO.builder().id(id).bets(new ArrayList<>()).isOpen(true).build();
  }

  @Test
  public void listRoulettesTest() {
    setUp();
    Assertions.assertNotNull(rouletteService.listRoulettes());
    remove();
  }

  @Test
  public void openingRouletteTest() {
    setUp();
    Assertions.assertNotNull(
        rouletteService.openingRoulette(ChangeRouletteStatusDTO.builder().id(id).build()));
    remove();
  }

  @Test
  public void updateRouletteTest() {
    setUp();
    rouletteDTO
        .getBets()
        .add(
            BetDTO.builder()
                .betNumber((short) 5)
                .betColorType(BetColorType.BLACK)
                .money(BigDecimal.valueOf(5000))
                .build());
    Assertions.assertNotNull(rouletteService.updateRoulette(rouletteDTO));
    remove();
  }

  @Test
  public void validateRouletteTest() {
    setUp();
    Assertions.assertThrows(RouletteException.class, () -> rouletteService.validateRoulette(id));
    remove();
  }

  @Test
  public void closeRouletteTest() {
    setUp();
    id = rouletteService.createRoulette();
    rouletteService.openingRoulette(ChangeRouletteStatusDTO.builder().id(id).build());
    Assertions.assertNotNull(rouletteService.closeRoulette(id));
    remove();
  }

  public void remove() {
    rouletteRepository.deleteById(id);
  }
}
