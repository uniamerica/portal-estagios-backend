package com.portaldeestagios.api.configs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("algafood.email")
@Builder
public class EmailProperties {
  private String remetente;
}