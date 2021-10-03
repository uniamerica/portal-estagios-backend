package com.portaldeestagios.api.user.integration;

import com.portaldeestagios.api.IntegrationTestConfig;
import com.portaldeestagios.api.dtos.model.user.UserDto;
import com.portaldeestagios.api.dtos.model.user.UserListDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserIntegrationTest extends IntegrationTestConfig {

  @Test
  void deveRetornarUmaListaDeUsuarios() throws Exception {
    var resultGet = mockMvc.perform(get("/application-user/")
                    .contentType(APPLICATION_JSON))
            .andExpect(status().isOk()).andReturn();

    String json = resultGet.getResponse().getContentAsString();

    UserListDto[] object = objectMapper.readValue(json, UserListDto[].class);

    assertEquals(8, object.length);
  }

  @Test
  void deveRetornarUmUsuarioComOMesmoIdDoEndereco() throws Exception {

    Long id = 1L;

    var resultGet = mockMvc.perform(get("/application-user/"+id)
                    .contentType(APPLICATION_JSON))
            .andExpect(status().isOk()).andReturn();

    String json = resultGet.getResponse().getContentAsString();

    UserDto user = objectMapper.readValue(json, UserDto.class);

    assertEquals("alexandre@gmail.com", user.getEmail());
    assertEquals(id, user.getId());
  }

  @Test
  void deveRetornarStatusNotFoundQuandoUsuarioNaoForEncontrado() throws Exception {

    long id = 9999L;

    var resultGet = mockMvc.perform(get("/application-user/"+id)
                    .contentType(APPLICATION_JSON)).andReturn();

    int status = resultGet.getResponse().getStatus();

    assertEquals(NOT_FOUND.value(), status);
  }
}
