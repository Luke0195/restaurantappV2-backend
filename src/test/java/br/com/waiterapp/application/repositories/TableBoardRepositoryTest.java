package br.com.waiterapp.application.repositories;

import br.com.waiterapp.application.domain.tableboard.TableBoard;
import br.com.waiterapp.application.factories.TableBoardFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TableBoardRepositoryTest {

    @Autowired
    private TableBoardRepository tableRepository;

    private TableBoard tableBoard;

    @Test
    @DisplayName("Should create an table when valid data is provided ")
    void saveShouldCreateTableBoardWhenValidDataIsProvided(){
        TableBoard tableBoard = TableBoardFactory.makeTableBoard();
        tableBoard = tableRepository.save(tableBoard);
        Assertions.assertNotNull(tableBoard);
        Assertions.assertNotNull(tableBoard.getId());
    }

}
