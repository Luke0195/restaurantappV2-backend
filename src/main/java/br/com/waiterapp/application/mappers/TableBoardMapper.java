package br.com.waiterapp.application.mappers;

import br.com.waiterapp.application.dtos.TableBoardDto;
import br.com.waiterapp.application.entities.TableBoard;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TableBoardMapper {
    TableBoardMapper INSTANCE = Mappers.getMapper(TableBoardMapper.class);

    TableBoard parseToEntity(TableBoardDto dto);

    TableBoardDto parseToDto(TableBoard tableBoard);
}
