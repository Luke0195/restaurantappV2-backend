package br.com.waiterapp.application.mappers.tableboard;

import br.com.waiterapp.application.dtos.tableboard.TableBoardDto;
import br.com.waiterapp.application.domain.tableboard.TableBoard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TableBoardMapper {
    TableBoardMapper INSTANCE = Mappers.getMapper(TableBoardMapper.class);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    TableBoard parseToEntity(TableBoardDto dto);

    TableBoardDto parseToDto(TableBoard tableBoard);
}
