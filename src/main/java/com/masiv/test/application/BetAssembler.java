package com.masiv.test.application;

import com.masiv.test.domain.documents.Bet;
import com.masiv.test.domain.dto.BetDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.BeanUtils;

public class BetAssembler {

  public static Bet toDocument(final BetDTO betDTO) {
    Bet bet = Bet.builder().build();
    if (!Objects.isNull(betDTO)) BeanUtils.copyProperties(betDTO, bet);
    return bet;
  }

  public static BetDTO toDto(final Bet bet) {
    BetDTO betDTO = BetDTO.builder().build();
    if (!Objects.isNull(bet)) BeanUtils.copyProperties(bet, betDTO);
    return betDTO;
  }

  public static List<BetDTO> toDto(final List<Bet> betList) {
    List<BetDTO> betDTOS = new ArrayList<>();
    if (!Objects.isNull(betList) && !betList.isEmpty()) {
      betList.stream().filter(Objects::nonNull).forEach(bet -> betDTOS.add(toDto(bet)));
    }
    return betDTOS;
  }

  public static List<Bet> toDocument(final List<BetDTO> betDTOS) {
    List<Bet> bets = new ArrayList<>();
    if (!Objects.isNull(betDTOS) && !betDTOS.isEmpty()) {
      betDTOS.stream().filter(Objects::nonNull).forEach(betDTO -> bets.add(toDocument(betDTO)));
    }
    return bets;
  }
}
