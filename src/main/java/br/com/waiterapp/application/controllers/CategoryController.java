package br.com.waiterapp.application.controllers;

import br.com.waiterapp.application.dtos.category.CategoryRequestDto;
import br.com.waiterapp.application.dtos.category.CategoryResponseDto;
import br.com.waiterapp.application.services.impl.CategoryServiceImpl;
import br.com.waiterapp.application.utils.httputils.HttpUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @PostMapping(value = "/category")
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto dto){
        CategoryResponseDto responseDto = categoryService.create(dto);
        URI uri = HttpUtil.makeUriBuilderComponent(responseDto.getId());
        return ResponseEntity.created(uri).body(responseDto);
    }

    @GetMapping(value="/categories")
    public ResponseEntity<List<CategoryResponseDto>> findAll(){
       List<CategoryResponseDto> categories = categoryService.findAllCached();
       return ResponseEntity.status(HttpStatus.OK).body(categories);
    }
}
