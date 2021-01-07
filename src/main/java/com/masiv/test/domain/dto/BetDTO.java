package com.masiv.test.domain.dto;

import com.masiv.test.domain.enums.betColor.BetColorType;
import com.masiv.test.domain.enums.betColor.BetColorTypeConverter;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Convert;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class BetDTO implements Serializable {
  private String id;

  @Convert(converter = BetColorTypeConverter.class)
  private BetColorType betColorType;

  private short betNumber;
  private BigDecimal money;
}
