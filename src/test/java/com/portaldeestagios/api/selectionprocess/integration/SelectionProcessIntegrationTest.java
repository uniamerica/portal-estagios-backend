package com.portaldeestagios.api.selectionprocess.integration;

import com.portaldeestagios.api.IntegrationTestConfig;
import com.portaldeestagios.api.dtos.inputDto.selectionprocess.InputSelectionProcess;
import com.portaldeestagios.api.selectionprocess.SelectionProcessController;
import com.portaldeestagios.api.selectionprocess.SelectionProcessEntity;
import com.portaldeestagios.api.selectionprocess.SelectionProcessRepository;
import com.portaldeestagios.api.selectionprocess.SelectionProcessStatusEnum;
import com.portaldeestagios.api.selectionprocess.factory.SelectionProcessFactory;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SelectionProcessIntegrationTest extends IntegrationTestConfig {

    @Autowired
    private SelectionProcessController selectionProcessController;
    @Autowired
    private SelectionProcessRepository selectionProcessRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Flyway flyway;

    @BeforeEach
    void setUp(){
        flyway.clean();
        flyway.migrate();
    }

    @Test
    void mustCreateSelectionProcessAnd () throws Exception {
        var inputSelectionProcess = modelMapper.map(SelectionProcessFactory.buildDefautlWithoutId(),
                InputSelectionProcess.class);

        mockMvc
                .perform(post("/selection-process")
                        .content(objectMapper.writeValueAsString(inputSelectionProcess))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.companyName", is(inputSelectionProcess.getCompanyName())))
                .andExpect(jsonPath("$.title", is(inputSelectionProcess.getTitle())));

    }

    @Test
    void mustFindListSelectionProcessWithStatusOpen () throws Exception {
        mockMvc
                .perform(get("/selection-process"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)));
    }

    @Test
    void mustFindOneSelectionProcess() throws Exception {
        Long id = 1L;
        mockMvc
                .perform(get("/selection-process/{selectionProcessId}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id" , is(id.intValue())))
                .andExpect(jsonPath("$.companyName", is("Itai")))
                .andExpect(jsonPath("$.title", is("Processo Seletivo do ITAI")))
                .andExpect(jsonPath("$.status", is(SelectionProcessStatusEnum.PENDENTE.toString())));
    }

    @Test
    void mustThrowExceptionWhenSelectionProcessNotFound() throws Exception {
        Long id = 6L;
        mockMvc
                .perform(get("/selection-process/{selectionProcessId}", id))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title", is("Recurso não encontrado")))
                .andExpect(jsonPath("$.detail", is(String.format("Não existe um cadastro de processo seletivo com código %S", id))));
    }

    @Test
    void deveMudarParaStatusOpenQuandoSelectionProcessStatusIgualAnalise() throws Exception {
        Long selectionProcessId = 1L;

        mockMvc
                .perform(patch("/selection-process/{selectionProcessId}/open", selectionProcessId))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc
                .perform(get("/selection-process/{selectionProcessId}", selectionProcessId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(SelectionProcessStatusEnum.ABERTO.toString())));
    }

    @Test
    void deveLancarExcessaoBadRequestQuandoTituloNaoForPreenchido() throws Exception {

        var inputSelectionProcess = modelMapper.map(SelectionProcessFactory.buildWithoutTitle(),
                InputSelectionProcess.class);
        mockMvc
                .perform(post("/selection-process")
                .content(objectMapper.writeValueAsString(inputSelectionProcess))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    void deveLancarExcessaoBadRequestQuandoCompanyNaoForPreenchido() throws Exception {

        var inputSelectionProcess = modelMapper.map(SelectionProcessFactory.buildWithoutCompanyName(),
                InputSelectionProcess.class);
        mockMvc
                .perform(post("/selection-process")
                        .content(objectMapper.writeValueAsString(inputSelectionProcess))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }
}
