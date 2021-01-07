package com.masiv.test.domain.enums.betColor;

import java.util.Objects;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BetColorTypeConverter implements AttributeConverter<BetColorType, String> {

  @Override
  public String convertToDatabaseColumn(BetColorType betColorType) {
    if (!Objects.isNull(betColorType)) return betColorType.getId();
    return BetColorType.NOT_APPLY.getId();
  }

  @Override
  public BetColorType convertToEntityAttribute(String s) {
    return BetColorType.findByPrimaryKey(s);
  }
}
