package com.masiv.test.services.impl;

import com.masiv.test.domain.dto.BetDTO;
import com.masiv.test.domain.dto.RouletteDTO;
import com.masiv.test.exceptions.BetException;
import com.masiv.test.services.BetService;
import com.masiv.test.services.RouletteService;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultBetService implements BetService {

  public static final String EMPTY_STRING = "";
  private final RouletteService rouletteService;
  private final MessageSource messageSource;

  @Value("${politics.max-money}")
  private BigDecimal MAX_MONEY;

  public DefaultBetService(RouletteService rouletteService, MessageSource messageSource) {
    this.rouletteService = rouletteService;
    this.messageSource = messageSource;
  }

  @Override
  public RouletteDTO createBet(RouletteDTO rouletteDTO) {
    if (!Objects.isNull(rouletteDTO)) {
      if (!Objects.isNull(rouletteDTO.getBets()) && !rouletteDTO.getBets().isEmpty()) {
        rouletteService.validateRoulette(
            rouletteDTO.getId() != null ? rouletteDTO.getId() : EMPTY_STRING);
        rouletteDTO.getBets().stream().filter(Objects::nonNull).forEach(this::validateBet);
        if (!rouletteDTO.isOpen()) rouletteDTO.setOpen(true);
        return rouletteService.updateRoulette(rouletteDTO);
      }
    }
    throw new BetException(
        messageSource.getMessage("bet.could.not.be.saved", null, Locale.getDefault()));
  }

  @Override
  public boolean validateBet(BetDTO betDTO) {
    if (!Objects.isNull(betDTO)) {
      if (Objects.isNull(betDTO.getId())) betDTO.setId(UUID.randomUUID().toString());
      if (!validateBetNumber(betDTO)) {
        throw new BetException(
            messageSource.getMessage("valid.roulete.numbers", null, Locale.getDefault()));
      }
      if (!validateBetMoney(betDTO)) {
        throw new BetException(
            String.format(
                messageSource.getMessage("valid.max.money", null, Locale.getDefault()),
                MAX_MONEY.toString()));
      }
      return true;
    }
    throw new BetException(
        messageSource.getMessage("bet.could.not.be.saved", null, Locale.getDefault()));
  }

  private boolean validateBetMoney(BetDTO betDTO) {
    return betDTO.getMoney().compareTo(MAX_MONEY) <= 0;
  }

  private boolean validateBetNumber(BetDTO betDTO) {
    return betDTO.getBetNumber() >= 0 && betDTO.getBetNumber() <= 36;
  }
}
