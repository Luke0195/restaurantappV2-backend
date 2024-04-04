package br.com.waiterapp.application.controllers;


import br.com.waiterapp.application.dtos.tableboard.TableBoardDto;
import br.com.waiterapp.application.services.impl.TableBoardServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "tableboards")
@AllArgsConstructor
public class TableBoardController {

    private TableBoardServiceImpl service;
    @PostMapping
    public ResponseEntity<TableBoardDto> createBoard(@Valid  @RequestBody TableBoardDto requestDto){
        TableBoardDto tableBoardResponseDto = service.create(requestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tableBoardResponseDto.getId()).toUri();
        return ResponseEntity.created(uri).body(tableBoardResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<TableBoardDto>> findAllTable(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value="direction", defaultValue = "ASC") String direction
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<TableBoardDto> tableBoardResponse = service.findAll(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(tableBoardResponse);
    }



}
