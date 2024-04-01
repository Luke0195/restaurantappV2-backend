package br.com.waiterapp.application.factories;

import br.com.waiterapp.application.dtos.TableBoardDto;
import br.com.waiterapp.application.entities.TableBoard;

import java.util.UUID;

public class TableBoardFactory {


    public static TableBoard makeTableBoard(){
        return TableBoard.builder().id(UUID.randomUUID()).name("any_name").build();
    }

    public static TableBoardDto makeTableBoardRequestDto(TableBoard entity){
        return TableBoardDto.builder().name(entity.getName()).build();
    }


}
