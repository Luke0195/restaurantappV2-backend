package br.com.waiterapp.application.factories;

import br.com.waiterapp.application.dtos.tableboard.TableBoardDto;
import br.com.waiterapp.application.domain.tableboard.TableBoard;

import java.util.UUID;

public class TableBoardFactory {


    public static TableBoard makeTableBoard(){
        return TableBoard.builder().name("any_name").build();
    }

    public static TableBoardDto makeTableBoardRequestDto(TableBoard entity){
        return TableBoardDto.builder().id(null).name(entity.getName()).build();
    }


}
