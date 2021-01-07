package com.masiv.test.domain.documents;

import com.masiv.test.domain.enums.betColor.BetColorType;
import com.masiv.test.domain.enums.betColor.BetColorTypeConverter;
import com.masiv.test.domain.enums.betNumber.BetNumberType;
import com.masiv.test.domain.enums.betNumber.BetNumberTypeConverter;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Convert;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Bet implements Serializable {
  private String id;

  @Convert(converter = BetColorTypeConverter.class)
  private BetColorType betColorType;

  @Convert(converter = BetNumberTypeConverter.class)
  private BetNumberType betNumberType;

  private BigDecimal money;
}
