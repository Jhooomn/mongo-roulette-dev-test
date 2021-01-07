package com.masiv.test.services;

import com.masiv.test.domain.dto.ChangeRouletteStatusDTO;

public interface RouletteService {
  String createRoulette();
  String openingRoulette(ChangeRouletteStatusDTO changeRouletteStatusDTO);
}
