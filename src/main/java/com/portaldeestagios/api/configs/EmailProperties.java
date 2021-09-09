package com.portaldeestagios.api.configs;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("portal.email")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailProperties {
  private String remetente;
}