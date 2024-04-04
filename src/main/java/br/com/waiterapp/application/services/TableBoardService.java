package br.com.waiterapp.application.services;

import br.com.waiterapp.application.dtos.tableboard.TableBoardDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TableBoardService {
    TableBoardDto create(TableBoardDto requestDto);
    Page<TableBoardDto> findAll(Pageable pageable);

}
