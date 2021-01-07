package com.masiv.test.domain.dto;

import com.masiv.test.domain.enums.betColor.BetColorType;
import com.masiv.test.domain.enums.betColor.BetColorTypeConverter;
import com.masiv.test.domain.enums.betNumber.BetNumberType;
import com.masiv.test.domain.enums.betNumber.BetNumberTypeConverter;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Convert;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BetDTO implements Serializable {
  private String id;

  @Convert(converter = BetColorTypeConverter.class)
  private BetColorType betColorType;

  @Convert(converter = BetNumberTypeConverter.class)
  private BetNumberType betNumberType;

  private BigDecimal money;
}
