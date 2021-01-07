package com.masiv.test.domain.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouletteDTO implements Serializable {
  private String id;
  private boolean isOpen;
  private List<BetDTO> bets;
}
