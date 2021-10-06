package com.portaldeestagios.api.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portaldeestagios.api.IntegrationTestConfig;
import com.portaldeestagios.api.registration.RegistrationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserIT extends IntegrationTestConfig {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Autowired
    private Flyway

    @BeforeEach

    @Test
    void mustCreateUserAndReturnSuccess() throws Exception {
        var registration = new RegistrationRequest();
        registration.setEmail("mi@gmail.com");
        registration.setPassword("senha");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/registration/student")
                        .content(asJsonString(registration))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void mustCreateUserAndReturnError() throws Exception {
        var registration = new RegistrationRequest();
        registration.setEmail("mi@gmail.com");
        registration.setPassword("senha");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/registration/student")
                        .content(asJsonString(registration))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/registration/student")
                        .content(asJsonString(registration))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
}
