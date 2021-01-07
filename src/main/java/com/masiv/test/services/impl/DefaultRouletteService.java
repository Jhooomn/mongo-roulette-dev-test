package com.masiv.test.services.impl;

import com.masiv.test.application.RouletteAssembler;
import com.masiv.test.domain.documents.Roulette;
import com.masiv.test.domain.dto.ChangeRouletteStatusDTO;
import com.masiv.test.domain.dto.RouletteDTO;
import com.masiv.test.exceptions.RouletteException;
import com.masiv.test.infrastructure.repositories.RouletteRepository;
import com.masiv.test.services.RouletteService;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
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
      throw new RouletteException(
          "Hubo un error al momento de crear la ruleta, por favor... intente más tarde");
    }
  }

  @Override
  public String openingRoulette(final ChangeRouletteStatusDTO changeRouletteStatusDTO) {
    RouletteDTO roulette = findRoulette(changeRouletteStatusDTO.getId());
    if (!Objects.isNull(roulette.getId()) && !roulette.getId().isEmpty()) {
      roulette.setOpen(true);
      updateRoulette(roulette);
      return "La operación fue exitosa";
    }
    throw new RouletteException("No se logró realizar la operación, intente más tarde");
  }

  private void updateRoulette(final RouletteDTO roulette) {
    try {
      rouletteRepository.save(RouletteAssembler.toDocument(roulette));
    } catch (Exception e) {
      throw new RouletteException("Ocurrió un error al momento de actualizar la ruleta");
    }
  }

  private RouletteDTO findRoulette(final String rouletteId) {
    Optional<Roulette> optionalRoulette = rouletteRepository.findById(rouletteId);
    if (optionalRoulette.isPresent()) return RouletteAssembler.toDTO(optionalRoulette.get());
    throw new RouletteException(
        "La ruleta que intenta buscar no se encuentra registrada en el sistema");
  }
}
