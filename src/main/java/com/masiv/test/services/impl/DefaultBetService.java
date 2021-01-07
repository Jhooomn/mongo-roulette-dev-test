package com.masiv.test.services.impl;

import com.masiv.test.domain.dto.BetDTO;
import com.masiv.test.domain.dto.RouletteDTO;
import com.masiv.test.exceptions.BetException;
import com.masiv.test.services.BetService;
import com.masiv.test.services.RouletteService;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultBetService implements BetService {

  public static final String EMPTY_STRING = "";
  private final RouletteService rouletteService;

  @Value("${politics.max-money}")
  private BigDecimal MAX_MONEY;

  public DefaultBetService(RouletteService rouletteService) {
    this.rouletteService = rouletteService;
  }

  @Override
  public RouletteDTO createBet(RouletteDTO rouletteDTO) {
    if (!Objects.isNull(rouletteDTO)) {
      if (!Objects.isNull(rouletteDTO.getBets()) && !rouletteDTO.getBets().isEmpty()) {
        rouletteService.validateRoulette(
            rouletteDTO.getId() != null ? rouletteDTO.getId() : EMPTY_STRING);
        rouletteDTO.getBets().stream().filter(Objects::nonNull).forEach(this::validateBet);
        return rouletteService.updateRoulette(rouletteDTO);
      }
    }
    throw new BetException("No se pudo registrar la apuesta.");
  }

  @Override
  public boolean validateBet(BetDTO betDTO) {
    if (!Objects.isNull(betDTO)) {
      if (Objects.isNull(betDTO.getId())) betDTO.setId(UUID.randomUUID().toString());
      if (!validateBetNumber(betDTO)) {
        throw new BetException("Los números válidos para apostar son del 0 al 36");
      }
      if (!validateBetMoney(betDTO)) {
        throw new BetException(
            String.format(
                "La cantidad maxima que puede suministrar es de %s dolares", MAX_MONEY.toString()));
      }
      return true;
    }
    throw new BetException("No es posible registrar las apuestas.");
  }

  private boolean validateBetMoney(BetDTO betDTO) {
    return betDTO.getMoney().compareTo(MAX_MONEY) <= 0;
  }

  private boolean validateBetNumber(BetDTO betDTO) {
    return betDTO.getBetNumber() >= 0 && betDTO.getBetNumber() <= 36;
  }
}
