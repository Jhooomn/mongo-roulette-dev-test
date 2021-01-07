package com.masiv.test.application;

import com.masiv.test.domain.documents.Roulette;
import com.masiv.test.domain.dto.RouletteDTO;
import java.util.ArrayList;
import java.util.Objects;
import org.springframework.beans.BeanUtils;

public class RouletteAssembler {

  public static Roulette toDocument(final RouletteDTO rouletteDTO) {
    Roulette roulette = Roulette.builder().build();
    if (!Objects.isNull(rouletteDTO)) {
      if (Objects.isNull(rouletteDTO.getBets())) rouletteDTO.setBets(new ArrayList<>());
      BeanUtils.copyProperties(rouletteDTO, roulette);
      roulette.setBets(BetAssembler.toDocument(rouletteDTO.getBets()));
    }
    return roulette;
  }

  public static RouletteDTO toDTO(final Roulette roulette) {
    RouletteDTO rouletteDTO = RouletteDTO.builder().build();
    if (!Objects.isNull(roulette)) {
      if (Objects.isNull(roulette.getBets())) roulette.setBets(new ArrayList<>());
      BeanUtils.copyProperties(roulette, rouletteDTO);
      rouletteDTO.setBets(BetAssembler.toDto(roulette.getBets()));
    }
    return rouletteDTO;
  }
}
