package br.com.waiterapp.application.services;

import br.com.waiterapp.application.dtos.category.CategoryRequestDto;
import br.com.waiterapp.application.dtos.category.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto create(CategoryRequestDto dto);
    List<CategoryResponseDto> findAll();
}
