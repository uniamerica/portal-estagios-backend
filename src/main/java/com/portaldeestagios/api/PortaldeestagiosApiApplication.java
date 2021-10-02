package com.portaldeestagios.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class PortaldeestagiosApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(PortaldeestagiosApiApplication.class, args);
  }

}
