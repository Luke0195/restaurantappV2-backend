package br.com.waiterapp.application.services;

import br.com.waiterapp.application.dtos.tableboard.TableBoardDto;
import br.com.waiterapp.application.domain.tableboard.TableBoard;
import br.com.waiterapp.application.factories.TableBoardFactory;
import br.com.waiterapp.application.repositories.TableBoardRepository;


import br.com.waiterapp.application.services.impl.TableBoardServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
public class TableBoardServiceTests {

    @InjectMocks
    private TableBoardServiceImpl service;
    @Mock
    private TableBoardRepository repository;


    private TableBoard tableBoard;

    @BeforeEach
    void setup() {
        this.tableBoard = TableBoardFactory.makeTableBoard();
        Mockito.when(repository.save(Mockito.any(TableBoard.class))).thenReturn(tableBoard);
    }

    @DisplayName("create should return a table when valid value is provided")
    @Test
    void createShouldReturnAnTableWhenValidDataIsProvided(){
        TableBoardDto tableBoardRequest = TableBoardFactory.makeTableBoardRequestDto(tableBoard);
        TableBoardDto tableBoardResponse = service.create(tableBoardRequest);
        Assertions.assertNotNull(tableBoardResponse);
        repository.save(tableBoard);
    }


}
