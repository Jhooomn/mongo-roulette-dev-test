package com.masiv.test.services;

import com.masiv.test.domain.dto.ChangeRouletteStatusDTO;
import com.masiv.test.domain.dto.RouletteDTO;

public interface RouletteService {
  String createRoulette();

  String openingRoulette(ChangeRouletteStatusDTO changeRouletteStatusDTO);

  RouletteDTO updateRoulette(RouletteDTO rouletteDTO);

  boolean validateRoulette(String rouletteId);

  void closeRoulette(String rouletteId);
}
