package com.masiv.test.services;

import com.masiv.test.domain.dto.BetDTO;
import com.masiv.test.domain.dto.RouletteDTO;

public interface BetService {
  RouletteDTO createBet(RouletteDTO rouletteDTO);
  boolean validateBet(BetDTO betDTO);
}
