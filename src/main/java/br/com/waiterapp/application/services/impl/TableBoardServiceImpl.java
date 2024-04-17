package br.com.waiterapp.application.services.impl;

import br.com.waiterapp.application.dtos.tableboard.TableBoardDto;
import br.com.waiterapp.application.domain.tableboard.TableBoard;
import br.com.waiterapp.application.mappers.tableboard.TableBoardMapper;
import br.com.waiterapp.application.repositories.TableBoardRepository;
import br.com.waiterapp.application.services.TableBoardService;
import br.com.waiterapp.application.services.exceptions.EntityAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TableBoardServiceImpl implements TableBoardService {

    private final TableBoardRepository tableBoardRepository;
    @Transactional
    @Override
    public TableBoardDto create(TableBoardDto requestDto) {
        Optional<TableBoard> tableBoardAlreadyExists = tableBoardRepository.findByName(requestDto.getName());
        if(tableBoardAlreadyExists.isPresent()) throw new EntityAlreadyExistsException("tableboard already exists");
        TableBoard entity = TableBoard.builder().name(requestDto.getName()).build();
        entity = tableBoardRepository.save(entity);
        return  TableBoardMapper.INSTANCE.parseToDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("tables")
    public Page<TableBoardDto> findAll(Pageable pageable) {
        Page<TableBoard> entities = tableBoardRepository.findAll(pageable);
        return entities.map(TableBoardMapper.INSTANCE::parseToDto);
    }
}
