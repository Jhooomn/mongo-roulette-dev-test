package com.masiv.test.services;

import com.masiv.test.domain.dto.BetDTO;
import com.masiv.test.domain.dto.ChangeRouletteStatusDTO;
import com.masiv.test.domain.dto.RouletteDTO;
import java.util.List;

public interface RouletteService {
  String createRoulette();

  String openingRoulette(ChangeRouletteStatusDTO changeRouletteStatusDTO);

  RouletteDTO updateRoulette(RouletteDTO rouletteDTO);

  boolean validateRoulette(String rouletteId);

  List<BetDTO> closeRoulette(String rouletteId);
}
