package com.portaldeestagios.api.selectionprocess.integration;

import com.portaldeestagios.api.IntegrationTestConfig;
import com.portaldeestagios.api.dtos.inputDto.selectionprocess.InputSelectionProcess;
import com.portaldeestagios.api.selectionprocess.SelectionProcessController;
import com.portaldeestagios.api.selectionprocess.factory.SelectionProcessFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SelectionProcessIntegrationTest extends IntegrationTestConfig {

    private InputSelectionProcess inputSelectionProcess;

    @Autowired
    private SelectionProcessController selectionProcessController;
    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp(){
         inputSelectionProcess = modelMapper.map(SelectionProcessFactory.buildDefautlWithoutId(),
                 InputSelectionProcess.class);
    }

    @Test
    void createSelectionProcess () throws Exception {


        mockMvc
                .perform(post("/selection-process")
                        .content(objectMapper.writeValueAsString(inputSelectionProcess))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
