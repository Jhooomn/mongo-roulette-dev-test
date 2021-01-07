package com.masiv.test.services.impl;

import com.masiv.test.application.RouletteAssembler;
import com.masiv.test.domain.documents.Roulette;
import com.masiv.test.domain.dto.BetDTO;
import com.masiv.test.domain.dto.ChangeRouletteStatusDTO;
import com.masiv.test.domain.dto.RouletteDTO;
import com.masiv.test.domain.enums.betColor.BetColorType;
import com.masiv.test.exceptions.RouletteException;
import com.masiv.test.infrastructure.repositories.RouletteRepository;
import com.masiv.test.services.RouletteService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class DefaultRouletteService implements RouletteService {

  private final RouletteRepository rouletteRepository;
  private final MessageSource messageSource;

  public DefaultRouletteService(
      RouletteRepository rouletteRepository, MessageSource messageSource) {
    this.rouletteRepository = rouletteRepository;
    this.messageSource = messageSource;
  }

  @Override
  public String createRoulette() {
    try {
      Roulette roulette =
          rouletteRepository.save(
              Roulette.builder().bets(new ArrayList<>()).id(UUID.randomUUID().toString()).build());
      return roulette.getId();
    } catch (Exception e) {
      throw new RouletteException(
          messageSource.getMessage("internal.server.error", null, Locale.getDefault()));
    }
  }

  @Override
  public String openingRoulette(final ChangeRouletteStatusDTO changeRouletteStatusDTO) {
    RouletteDTO roulette = findRoulette(changeRouletteStatusDTO.getId());
    if (!Objects.isNull(roulette.getId()) && !roulette.getId().isEmpty()) {
      roulette.setOpen(true);
      updateRoulette(roulette);
      return messageSource.getMessage("valid.succesfully.operation", null, Locale.getDefault());
    }
    throw new RouletteException(
        messageSource.getMessage("valid.error.operation", null, Locale.getDefault()));
  }

  @Override
  public RouletteDTO updateRoulette(final RouletteDTO roulette) {
    try {
      return RouletteAssembler.toDTO(
          rouletteRepository.save(RouletteAssembler.toDocument(roulette)));
    } catch (Exception e) {
      throw new RouletteException(
          messageSource.getMessage(
              "valid.unexpected.error.while.updating.roulette", null, Locale.getDefault()));
    }
  }

  @Override
  public boolean validateRoulette(String rouletteId) {
    if (!Objects.isNull(rouletteId) && !rouletteId.isEmpty()) {
      Optional<Roulette> optionalRoulette = rouletteRepository.findById(rouletteId);
      if (optionalRoulette.isPresent()) {
        if (!optionalRoulette.get().isOpen()) {
          throw new RouletteException(
              messageSource.getMessage("valid.roulette.does.not.open", null, Locale.getDefault()));
        } else {
          return true;
        }
      } else {
        throw new RouletteException(
            messageSource.getMessage(
                "valid.roulette.does.not.persisted", null, Locale.getDefault()));
      }
    }
    return false;
  }

  @Override
  public List<BetDTO> closeRoulette(final String rouletteId) {
    short winningBetNumber = generateWinningBetNumber();
    RouletteDTO roulette = findRoulette(rouletteId);
    if (roulette.isOpen()) {
      roulette.setOpen(false);
      updateRoulette(roulette);
      return validateWinningBets(roulette, winningBetNumber);
    }
    throw new RouletteException(
        messageSource.getMessage("valid.roulette.is.already.closed", null, Locale.getDefault()));
  }

  @Override
  public List<RouletteDTO> listRoulettes() {
    return rouletteRepository.findAll().stream()
        .filter(Objects::nonNull)
        .map(RouletteAssembler::toDTO)
        .collect(Collectors.toList());
  }

  private List<BetDTO> validateWinningBets(RouletteDTO rouletteDTO, final short winningNumber) {
    List<BetDTO> betDTOS = new ArrayList<>();
    rouletteDTO.getBets().stream()
        .filter(Objects::nonNull)
        .filter(
            betDTO ->
                validateWinningNumberMatch(winningNumber, betDTO)
                    || validateColorMatchWinningNumber(betDTO, winningNumber))
        .forEach(
            betDTO -> {
              betDTO.setWinningBetNumber(winningNumber);
              betDTOS.add(betDTO);
            });
    updateRoulette(rouletteDTO);
    return betDTOS;
  }

  private boolean validateWinningNumberMatch(short winningNumber, BetDTO betDTO) {
    if (betDTO.getBetNumber() == winningNumber) {
      betDTO.setWinningMoney(betDTO.getMoney().multiply(BigDecimal.valueOf(5L)));
      return true;
    }
    return false;
  }

  private boolean validateColorMatchWinningNumber(BetDTO betDTO, short winningNumber) {
    if (((winningNumber % 2) == 0) && betDTO.getBetColorType().equals(BetColorType.RED)
        || ((winningNumber % 2) != 0) && betDTO.getBetColorType().equals(BetColorType.BLACK)) {
      betDTO.setWinningMoney(betDTO.getMoney().multiply(BigDecimal.valueOf(1.8)));
      return true;
    }
    return false;
  }

  private RouletteDTO findRoulette(final String rouletteId) {
    Optional<Roulette> optionalRoulette = rouletteRepository.findById(rouletteId);
    if (optionalRoulette.isPresent()) return RouletteAssembler.toDTO(optionalRoulette.get());
    throw new RouletteException(
        messageSource.getMessage("valid.roulette.does.not.persisted", null, Locale.getDefault()));
  }

  private short generateWinningBetNumber() {
    return (short) ThreadLocalRandom.current().nextInt(0, 36 + 1);
  }
}
