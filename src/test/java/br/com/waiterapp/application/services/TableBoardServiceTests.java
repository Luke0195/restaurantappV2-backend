package br.com.waiterapp.application.services;

import br.com.waiterapp.application.Application;
import br.com.waiterapp.application.dtos.tableboard.TableBoardDto;
import br.com.waiterapp.application.domain.tableboard.TableBoard;
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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(MockitoExtension.class)
public class TableBoardServiceTests {

    @InjectMocks
    private TableBoardServiceImpl service;
    @Mock
    private TableBoardRepository repository;

    private TableBoard existingTableBoard;

    @BeforeEach
    void setup() {
        TableBoard tableBoard = TableBoardFactory.makeTableBoard();
        this.existingTableBoard = TableBoardFactory.makeTableBoard();

    }



}
