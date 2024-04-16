package br.com.waiterapp.application.controllers;

import br.com.waiterapp.application.Application;
import br.com.waiterapp.application.domain.tableboard.TableBoard;
import br.com.waiterapp.application.dtos.tableboard.TableBoardDto;
import br.com.waiterapp.application.services.impl.TableBoardServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class TableBoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private TableBoardServiceImpl service;

    private TableBoardDto dto;

    @Autowired
    private ObjectMapper objectMapper;

    void setup(){
        dto = TableBoardDto.builder().id(UUID.randomUUID()).name(String.format("mesa %s", UUID.randomUUID())).build();
        Mockito.when(service.create(Mockito.any(TableBoardDto.class))).thenReturn(dto);
    }
    /*

    @DisplayName("POST - Should create a table when valid data is provided")
    @Test
    public void createShouldCreateATableWhenValidDataIsProvided() throws Exception{

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ3YWl0ZXItYXBwLWFwaSIsInN1YiI6Imx1Y2FzQG1haWwuY29tIiwiZXhwIjoxNzEyNTA1NjY1fQ.97rio8wBZ21gFwlHxraXa_jMaA52J8sz565D8seKulg";

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/tableboards")
                .content(objectMapper.writeValueAsString(dto)).header("authorization", String.format("Bearer %s", token))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated()).andExpect(forwardedUrl(null));

        TableBoardDto response = service.create(dto);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertNotNull(response.getName());

    }
    */

}
