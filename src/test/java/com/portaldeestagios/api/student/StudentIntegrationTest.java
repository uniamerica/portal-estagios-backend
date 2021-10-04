package com.portaldeestagios.api.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portaldeestagios.api.dtos.inputDto.student.StudentInput;
import com.portaldeestagios.api.dtos.model.student.StudentListDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentIntegrationTest {

    @Autowired
    private StudentController studentController;

    @Autowired
    private MockMvc mockMvc;

    private StudentInput studentInput;

    @Autowired
    private ObjectMapper objectMapper;

    private String jwt;

    private static final String ADMIN = "admin@gmail.com";

    private static final String STUDENT = "thaina@gmail.com";

    void login(String username) throws Exception {
        studentInput = StudentFactory.studentBuilderToBeUpdate();
        MvcResult signIn = mockMvc
                .perform(post("/login")
                        .content(String.format("{\"username\": \"%s\", \"password\": \"password\"}", username))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        jwt = signIn.getResponse().getHeader("Authorization");
    }

    @Test
    void mustUpdateAnUserCreatingAnStudent() throws Exception {
        login(STUDENT);
        mockMvc
                .perform(put("/students/")
                        .content(objectMapper.writeValueAsString(studentInput))
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", jwt))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void mustFindAnStudentByItsTokenProfile() throws Exception {
        login(STUDENT);
        mockMvc
                .perform(get("/students/profile")
                .header("Authorization", jwt))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void mustFindAnStudentByItsToken() throws Exception {
        login(STUDENT);
        mockMvc
                .perform(get("/students/token")
                .header("Authorization", jwt))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void mustReturnAllStudents() throws Exception {
        login(ADMIN);
        var resultGet = mockMvc.perform(get("/students/")
                .header("Authorization", jwt))
                .andExpect(status().isOk()).andReturn();

        String json = resultGet.getResponse().getContentAsString();

        StudentListDto[] object = objectMapper.readValue(json, StudentListDto[].class);

        assertEquals(5, object.length);
    }
}