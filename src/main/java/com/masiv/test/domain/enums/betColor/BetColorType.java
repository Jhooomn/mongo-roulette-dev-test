package com.masiv.test.domain.enums.betColor;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Collection;
import java.util.HashMap;
import lombok.Getter;

@Getter
public enum BetColorType {
  RED(BetColorType.RED_CODE, "Red"),
  BLACK(BetColorType.BLACK_CODE, "Black"),
  NOT_APPLY(BetColorType.NOT_APPLY_CODE, "NOT APPLY.");

  private static final String RED_CODE = "1";
  private static final String BLACK_CODE = "2";
  private static final String NOT_APPLY_CODE = "99";

  private static final HashMap<String, BetColorType> ENUM_MAP_BY_CODE = new HashMap<>();

  @JsonValue private final String id;
  private final String description;

  static {
    ENUM_MAP_BY_CODE.put(RED_CODE, RED);
    ENUM_MAP_BY_CODE.put(BLACK_CODE, BLACK);
    ENUM_MAP_BY_CODE.put(NOT_APPLY_CODE, NOT_APPLY);
  }

  BetColorType(String id, String description) {
    this.id = id;
    this.description = description;
  }

  public static BetColorType findByPrimaryKey(String id) {
    if (id != null && ENUM_MAP_BY_CODE.containsKey(id)) {
      return ENUM_MAP_BY_CODE.get(id);
    } else {
      return NOT_APPLY;
    }
  }

  public static Collection<BetColorType> getList() {
    return ENUM_MAP_BY_CODE.values();
  }
}
