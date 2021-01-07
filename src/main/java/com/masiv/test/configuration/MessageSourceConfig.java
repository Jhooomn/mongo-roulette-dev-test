package com.masiv.test.configuration;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {
  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource =
        new ReloadableResourceBundleMessageSource();
    messageSource.setDefaultEncoding("UTF-8");
    messageSource.setUseCodeAsDefaultMessage(true);
    messageSource.setBasenames(
        "classpath:/messages/validation/messages", "classpath:/messages/business/messages");
    return messageSource;
  }

  @Bean
  public void locale() {
    Locale.setDefault(new Locale("es", "ES"));
  }
}
