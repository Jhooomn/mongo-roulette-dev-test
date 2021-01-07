package com.masiv.test.services.impl;

import com.masiv.test.domain.documents.Roulette;
import com.masiv.test.infrastructure.repositories.RouletteRepository;
import com.masiv.test.services.RouletteService;
import java.util.ArrayList;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultRouletteService implements RouletteService {

  private final RouletteRepository rouletteRepository;

  public DefaultRouletteService(RouletteRepository rouletteRepository) {
    this.rouletteRepository = rouletteRepository;
  }

  @Override
  public String createRoulette() {
    try {
      Roulette roulette =
          rouletteRepository.save(
              Roulette.builder().bets(new ArrayList<>()).id(UUID.randomUUID().toString()).build());
      return roulette.getId();
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }
}
