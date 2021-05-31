package com.portaldeestagios.api.configs;


import io.swagger.models.auth.In;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.net.HttpHeaders;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig implements WebMvcConfigurer {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//            .paths(PathSelectors.any())
            .build()
            .securitySchemes(Collections.singletonList(new ApiKey("Token Access", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
            .securityContexts(Collections.singletonList(securityContext()))
            .apiInfo(createApiInfo())
            .tags(new Tag("Student", "Gerencia os estudantes"));
  }

  @Override
  public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
    registry
            .addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
    registry
            .addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  private SecurityContext securityContext(){
    return SecurityContext.builder()
              .securityReferences(defaultAuth())
              .forPaths(PathSelectors.any())
              .build();
  }

  private List<SecurityReference> defaultAuth(){
    AuthorizationScope authorizationScope
            = new AuthorizationScope("ADMIN", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Collections.singletonList(new SecurityReference("Token Access", authorizationScopes));
  }

  private ApiInfo createApiInfo(){
    return new ApiInfoBuilder()
            .title("Documentação Portal de Estágios - Spring Boot REST API")
            .description("API de gerenciamento do Portal de Estágios do ITAI")
            .version("1.0.0")
            .contact(new Contact("ITAI - Instituto de Tecnologia Aplicada e Inovação", "https://www.itai.org.br", "contato@itai.org.br"))
            .build();
  }
}