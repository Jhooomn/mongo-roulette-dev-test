package com.masiv.test.domain.documents;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Roulette implements Serializable {
  @Id private String id;
  private boolean isOpen;
  private List<Bet> bets;
}
