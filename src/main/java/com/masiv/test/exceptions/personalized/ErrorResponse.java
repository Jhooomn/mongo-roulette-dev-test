package com.masiv.test.exceptions.personalized;

import com.masiv.test.domain.enums.exceptionType.ExceptionCode;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse implements Serializable {
  private ExceptionCode exceptionCode;
  private String message;
}
