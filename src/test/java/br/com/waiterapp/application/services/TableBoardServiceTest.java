package br.com.waiterapp.application.services;

import br.com.waiterapp.application.dtos.TableBoardDto;
import br.com.waiterapp.application.entities.TableBoard;
import br.com.waiterapp.application.factories.TableBoardFactory;
import br.com.waiterapp.application.repositories.TableBoardRepository;

import br.com.waiterapp.application.services.exceptions.EntityAlreadyExistsException;
import br.com.waiterapp.application.services.impl.TableBoardServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
public class TableBoardServiceTest {

    @InjectMocks
    private TableBoardServiceImpl service;
    @Mock
    private TableBoardRepository repository;

    private TableBoard tableBoard;

    private TableBoard existingTableBoard;

    @BeforeEach
    void setup() {
        this.tableBoard = TableBoardFactory.makeTableBoard();
        this.existingTableBoard = TableBoardFactory.makeTableBoard();
        Mockito.when(repository.save(Mockito.any(TableBoard.class))).thenReturn(tableBoard);
    }

    @DisplayName("Should create an tableboard with valid value is provided")
    @Test
    void createShouldCreateAnTableBoardWhenValidDataIsProvided(){
        TableBoardDto requestDto = TableBoardFactory.makeTableBoardRequestDto(this.existingTableBoard);
        TableBoardDto result = service.create(requestDto);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertNotNull(result.getName());
    }

    @DisplayName("Should throw EntityAlreadyExistsException when name exists")
    @Test
    void createShouldThrowEntityAlreadyExistsExceptionWhenTableBoardNameAlreadyExists(){
        Mockito.when(repository.findByName(existingTableBoard.getName())).thenThrow(EntityAlreadyExistsException.class);
        TableBoardDto requestDto = TableBoardFactory.makeTableBoardRequestDto(existingTableBoard);
        Assertions.assertThrows(EntityAlreadyExistsException.class, () -> {
            service.create(requestDto);
        });

    }

}
