package br.com.waiterapp.application.mappers.tableboard;

import br.com.waiterapp.application.dtos.tableboard.TableBoardDto;
import br.com.waiterapp.application.domain.tableboard.TableBoard;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TableBoardMapper {
    TableBoardMapper INSTANCE = Mappers.getMapper(TableBoardMapper.class);

    TableBoard parseToEntity(TableBoardDto dto);

    TableBoardDto parseToDto(TableBoard tableBoard);
}
