package ru.maxizenit.socialmediaapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Конфигурация для маппинга. */
@Configuration
public class MappingConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
