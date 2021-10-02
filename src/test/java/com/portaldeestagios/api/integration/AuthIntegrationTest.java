package com.portaldeestagios.api.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portaldeestagios.api.dtos.model.user.UserListDto;
import com.portaldeestagios.api.security.jwt.UsernamePasswordAuthenticationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  public String jwt;

  @Test
  void deveRetornarUmaListaDeUsersQuandoLogadoComoRoleAdmin() throws Exception {
    loginWithAdmin();

    MvcResult resultGet = mockMvc.perform(get("/application-user/")
                    .header(HttpHeaders.AUTHORIZATION, jwt)
                    .contentType("application/json"))
                    .andExpect(status().isOk()).andReturn();

    String json = resultGet.getResponse().getContentAsString();

    UserListDto[] object = objectMapper.readValue(json, UserListDto[].class);

    Assertions.assertEquals(8, object.length);
  }

  @Test
  void deveRetornarStatusForbbidenQuandoBuscarUmaListaDeUsersLogadoComoRoleStudent() throws Exception {
    loginWithStudent();
    mockMvc.perform(get("/application-user/")
                    .header(HttpHeaders.AUTHORIZATION, jwt)
                    .contentType("application/json"))
                    .andExpect(status().isForbidden());

  }

  private void loginWithAdmin() throws Exception {
    UsernamePasswordAuthenticationRequest user = new UsernamePasswordAuthenticationRequest();
    user.setUsername("admin@gmail.com");
    user.setPassword("password");

    MvcResult resultPost = mockMvc.perform(post("/login")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(user)))
                    .andExpect(status().isOk()).andReturn();

    jwt = resultPost.getResponse().getHeader(HttpHeaders.AUTHORIZATION);
  }

  private void loginWithStudent() throws Exception {
    UsernamePasswordAuthenticationRequest user = new UsernamePasswordAuthenticationRequest();
    user.setUsername("alexandre@gmail.com");
    user.setPassword("password");

    MvcResult resultPost = mockMvc.perform(post("/login")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(user)))
                    .andExpect(status().isOk()).andReturn();
    jwt = resultPost.getResponse().getHeader(HttpHeaders.AUTHORIZATION);
  }
}
