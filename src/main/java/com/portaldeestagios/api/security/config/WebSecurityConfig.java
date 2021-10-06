package com.portaldeestagios.api.security.config;

import com.portaldeestagios.api.customhandlers.CustomAccessDeniedHandler;
import com.portaldeestagios.api.customhandlers.CustomAuthenticationEntryPoint;
import com.portaldeestagios.api.security.jwt.JwtConfig;
import com.portaldeestagios.api.security.jwt.JwtTokenVerifier;
import com.portaldeestagios.api.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.portaldeestagios.api.security.jwt.JwtUtils;
import com.portaldeestagios.api.user.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.Collections;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final ApplicationUserService applicationUserService;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final SecretKey secretKey;
  private final JwtConfig jwtConfig;
  private final JwtUtils jwtUtils;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
            .cors().and().csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .headers().frameOptions().disable()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET,
                    "/",
                    "/h2-console/**",
                    "index",
                    "/error",
                    "/css/*",
                    "/js/*",
                    "/login",
                    "/courses",
                    "/registration/**",
                    "/selection-process",
                    "/selection-process/**",
                    "/swagger-ui/**").permitAll()
            .antMatchers(HttpMethod.POST,
            "/registration/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey, jwtUtils))
            .addFilterBefore(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
            .formLogin()
            .loginPage("/login")
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
            .accessDeniedHandler(new CustomAccessDeniedHandler());
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/api/v1/selection-process",
            "/api/v1/selection-process/**",
            "/h2-console/**");
  }
  @Override
  protected void configure(AuthenticationManagerBuilder auth)  {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(bCryptPasswordEncoder);
    provider.setUserDetailsService(applicationUserService);
    provider.setHideUserNotFoundExceptions(false);
    return provider;
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
    configuration.addExposedHeader("Authorization");
    configuration.setAllowedHeaders(Collections.singletonList("*"));
    configuration.setAllowedMethods(Collections.singletonList("*"));
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}