package com.masiv.test.service;

import com.masiv.test.domain.dto.BetDTO;
import com.masiv.test.domain.dto.ChangeRouletteStatusDTO;
import com.masiv.test.domain.dto.RouletteDTO;
import com.masiv.test.domain.enums.betColor.BetColorType;
import com.masiv.test.infrastructure.repositories.RouletteRepository;
import com.masiv.test.services.BetService;
import com.masiv.test.services.RouletteService;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class BetServiceTest {
  @Autowired private BetService betService;
  @Autowired private RouletteService rouletteService;
  @Autowired private RouletteRepository rouletteRepository;
  private String id;
  private RouletteDTO rouletteDTO;

  public void setUp() {
    id = rouletteService.createRoulette();
    rouletteDTO = RouletteDTO.builder().id(id).bets(new ArrayList<>()).isOpen(true).build();
  }

  @Test
  public void createAndValidateBet() {
    setUp();
    rouletteDTO
        .getBets()
        .add(
            BetDTO.builder()
                .betNumber((short) 5)
                .betColorType(BetColorType.BLACK)
                .money(BigDecimal.valueOf(5000))
                .build());
    rouletteService.openingRoulette(ChangeRouletteStatusDTO.builder().id(id).build());
    Assertions.assertNotNull(betService.createBet(rouletteDTO));
    Assertions.assertTrue(betService.validateBet(rouletteDTO.getBets().get(0)));
    remove();
  }

  public void remove() {
    rouletteRepository.deleteById(id);
  }
}
