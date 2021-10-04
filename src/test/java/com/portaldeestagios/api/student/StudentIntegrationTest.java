package com.portaldeestagios.api.student;

import com.jayway.jsonpath.JsonPath;
import com.portaldeestagios.api.IntegrationTestConfig;
import com.portaldeestagios.api.dtos.inputDto.student.StudentInput;
import com.portaldeestagios.api.dtos.model.student.StudentListDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StudentIntegrationTest  extends IntegrationTestConfig {

    @Autowired
    private StudentController studentController;

    private StudentInput studentInput;

//    private Authentication authentication;
//    private String jwt;
//    private String type;

    @BeforeEach
    void setUp() throws Exception {
        studentInput = StudentFactory.studentBuilderToBeUpdate();
        MvcResult signIn = mockMvc
                .perform(post("/login")
                        .content("{ \"email\": \"admin@admin.com\", \"password\": \"asadmin\" }")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.token").isNotEmpty()).andReturn();

        jwt = JsonPath.read(signIn.getResponse().getContentAsString(), "$.token");
        type = JsonPath.read(signIn.getResponse().getContentAsString(), "$.type");
    }

    @Test
    void mustUpdateAnUserCreatingAnStudent() throws Exception {
        mockMvc
                .perform(put("/students/")
                        .content(objectMapper.writeValueAsString(studentInput))
                        .contentType(APPLICATION_JSON))
//                        .header("Authorization", type.concat(" " + jwt)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void mustFindAnStudentByItsTokenProfile() throws Exception {

        mockMvc
                .perform(get("/students/profile"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void mustFindAnStudentByItsToken() throws Exception {

        mockMvc
                .perform(get("/students/token"))
//                        .header("Authorization", type.concat(" " + jwt)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void mustReturnAllStudents() throws Exception {
        var resultGet = mockMvc.perform(get("/students/")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String json = resultGet.getResponse().getContentAsString();

        StudentListDto[] object = objectMapper.readValue(json, StudentListDto[].class);

        assertEquals(5, object.length);
    }
}