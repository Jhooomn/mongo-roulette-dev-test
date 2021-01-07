package com.masiv.test.domain.documents;

import com.masiv.test.domain.enums.betColor.BetColorType;
import com.masiv.test.domain.enums.betColor.BetColorTypeConverter;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Convert;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Bet implements Serializable {
  @Id private String id;

  @Convert(converter = BetColorTypeConverter.class)
  private BetColorType betColorType;

  private short betNumber;
  private BigDecimal money;

  private BigDecimal winningMoney;
  private short winningBetNumber;
}
