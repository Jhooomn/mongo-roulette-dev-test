package com.masiv.test.contollers;

import com.masiv.test.domain.dto.ChangeRouletteStatusDTO;
import com.masiv.test.services.RouletteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/roulette")
@RestController
public class RouletteController {

  private final RouletteService rouletteService;

  public RouletteController(RouletteService rouletteService) {
    this.rouletteService = rouletteService;
  }

  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> createRoulette() {
    return new ResponseEntity<>(rouletteService.createRoulette(), HttpStatus.OK);
  }

  @PutMapping(value = "/open-status")
  public ResponseEntity<String> openRouletteStatus(
      @RequestBody ChangeRouletteStatusDTO changeRouletteStatusDTO) {
    return new ResponseEntity<>(
        rouletteService.openingRoulette(changeRouletteStatusDTO), HttpStatus.OK);
  }
}
