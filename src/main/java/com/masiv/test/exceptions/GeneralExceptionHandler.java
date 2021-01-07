package com.masiv.test.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.masiv.test.domain.enums.exceptionType.ExceptionCode;
import com.masiv.test.exceptions.personalized.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class GeneralExceptionHandler {
  private final MessageSource messageSource;

  @ExceptionHandler({InvalidFormatException.class, HttpMessageNotReadableException.class})
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public ErrorResponse tryingExceptionType(InvalidFormatException exceptionToCatch) {
    final String invalidParameter = exceptionToCatch.getPathReference().split("\"")[1];
    generateLog(exceptionToCatch.getMessage());
    return ErrorResponse.builder()
        .exceptionCode(ExceptionCode.BUSINESS)
        .message(
            String.format(
                "No se reconoce el parametro '%s' dentro del atributo %s.",
                exceptionToCatch.getValue(), invalidParameter))
        .build();
  }

  @ExceptionHandler({BetException.class})
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public ErrorResponse tryingExceptionType(BetException exceptionToCatch) {
    generateLog(exceptionToCatch.getMessage());
    return ErrorResponse.builder()
        .exceptionCode(ExceptionCode.BUSINESS)
        .message(exceptionToCatch.getMessage())
        .build();
  }

  @ExceptionHandler({RouletteException.class})
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public ErrorResponse tryingExceptionType(RouletteException exceptionToCatch) {
    generateLog(exceptionToCatch.getMessage());
    return ErrorResponse.builder()
        .exceptionCode(ExceptionCode.BUSINESS)
        .message(exceptionToCatch.getMessage())
        .build();
  }

  private void generateLog(final String message) {
    log.error(message);
  }
}
