package com.masiv.test.contollers;

import com.masiv.test.domain.dto.RouletteDTO;
import com.masiv.test.services.BetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/bet")
@RestController
public class BetController {
  private final BetService betService;

  public BetController(BetService betService) {
    this.betService = betService;
  }

  @PostMapping
  public ResponseEntity<RouletteDTO> createBet(@RequestBody RouletteDTO rouletteDTO) {
    return new ResponseEntity<>(betService.createBet(rouletteDTO), HttpStatus.OK);
  }
}
