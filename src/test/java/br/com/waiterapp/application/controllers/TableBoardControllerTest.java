package br.com.waiterapp.application.controllers;

import br.com.waiterapp.application.dtos.TableBoardDto;
import br.com.waiterapp.application.factories.TableBoardFactory;
import br.com.waiterapp.application.services.impl.TableBoardServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TableBoardControllerTest {
    @Autowired
    private MockMvc mockMvc;



    @MockBean
    private TableBoardServiceImpl service;
    private TableBoardDto tableBoardDto;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
     //   Mockito.when(service.create(tableBoardDto)).thenReturn(TableBoardFactory.makeTableBoardResponseDto(TableBoardFactory.makeTableBoard()));

    }
    @DisplayName("POST - create should return a table when valid data is provided")
    @Test
    void createShouldReturnATableWhenValidDataIsProvided() throws Exception{

        this.tableBoardDto = TableBoardFactory.makeTableBoardRequestDto(TableBoardFactory.makeTableBoard());
        String jsonBody = objectMapper.writeValueAsString(this.tableBoardDto);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/v1/tableboardstableboards")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andExpect(status().isNotFound());

    }

}
